package com.dev.apprx.camera.ui
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Environment
import android.provider.MediaStore
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor

class CameraViewModel : ViewModel() {

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> get() = _showDialog

    fun showCustomDialog() {
        _showDialog.value = true
    }

    fun closeCustomDialog() {
        _showDialog.value = false
    }

    fun takePicture(cameraController: LifecycleCameraController, executor: Executor, context: Context) {
        val file = File.createTempFile("IMG_RX", ".jpg")
        val outputDirectory = ImageCapture.OutputFileOptions.Builder(file).build()
        cameraController.takePicture(
            outputDirectory,
            executor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    // Obtener la URI del archivo guardado
                    val savedUri = outputFileResults.savedUri

                    // Si la URI es nula, la imagen se guardó en el archivo temporal
                    if (savedUri != null) {
                        // Ahora, guarda la imagen en la galería utilizando MediaStore
                        val contentValues = ContentValues().apply {
                            put(MediaStore.Images.Media.DISPLAY_NAME, "IMG_RX.jpg")
                            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                        }

                        val contentResolver = context.contentResolver
                        val imageUri = contentResolver.insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            contentValues
                        )

                        if (imageUri != null) {
                            val outputStream = contentResolver.openOutputStream(imageUri)
                            val inputStream = FileInputStream(file)
                            if (outputStream != null) {
                                inputStream.copyTo(outputStream)
                            }
                            inputStream.close()
                            outputStream?.close()
                        }

                        // Borra el archivo temporal si se guardó en la galería
                        file.delete()

                        // Notifica a la galería que se ha agregado una nueva imagen
                        context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, imageUri))
                    } else {
                        // Manejar el caso en que no se pudo guardar la imagen en la galería
                        println("Error al guardar la imagen en la galería")
                    }
                }

                override fun onError(exception: ImageCaptureException) {
                    // Manejar errores de captura de imagen
                    println("Error al capturar la imagen: ${exception.message}")
                }
            }
        )
    }


}
