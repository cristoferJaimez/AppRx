package com.dev.apprx.camera.ui
import android.os.Environment
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor

class CameraViewModel : ViewModel() {

    private var imageCapture: ImageCapture? = null

    @OptIn(ExperimentalPermissionsApi::class)
    private val _permissionState = MutableLiveData<PermissionState>()
    @OptIn(ExperimentalPermissionsApi::class)
    val permissionState: LiveData<PermissionState> = _permissionState

    @OptIn(ExperimentalPermissionsApi::class)
    fun updatePermissionState(newState: PermissionState) {
        _permissionState.postValue(newState)
    }

    fun setImageCapture(imageCapture: ImageCapture) {
        this.imageCapture = imageCapture
    }

    fun captureImage(executor: Executor) {
        val imageCapture = this.imageCapture ?: return

        viewModelScope.launch(Dispatchers.IO) {
            val file = createImageFile()
            val outputOptions = ImageCapture.OutputFileOptions.Builder(file).build()

            try {
                imageCapture.takePicture(outputOptions, executor, object : ImageCapture.OnImageCapturedCallback(),
                    ImageCapture.OnImageSavedCallback {
                    override fun onCaptureSuccess(image: ImageProxy) {
                        super.onCaptureSuccess(image)
                    }

                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        TODO("Not yet implemented")
                    }

                    override fun onError(exception: ImageCaptureException) {
                        super.onError(exception)
                    }
                })
            } catch (e: ImageCaptureException) {
                e.printStackTrace()
            }
        }
    }

    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val storageDir: File? = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
    }
}
