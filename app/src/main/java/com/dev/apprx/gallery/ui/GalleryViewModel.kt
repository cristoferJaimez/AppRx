package com.dev.apprx.gallery.ui


import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.inappmessaging.model.ImageData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GalleryViewModel : ViewModel() {
    private val galleryImages: MutableLiveData<List<GalleryImage>> = MutableLiveData()

    // Devuelve LiveData que puede ser observado desde la UI
    fun getGalleryImages(): LiveData<List<GalleryImage>> {
        return galleryImages
    }

    // Carga las imágenes de la galería del teléfono
    fun loadGalleryImages(contentResolver: ContentResolver) {
        viewModelScope.launch(Dispatchers.IO) {
            val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_ADDED
            )

            val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"
            val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

            val images = mutableListOf<GalleryImage>()

            val cursor: Cursor? = contentResolver.query(
                uri,
                projection,
                null,
                null,
                sortOrder
            )

            cursor?.use {
                while (cursor.moveToNext()) {
                    val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                    val name =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                    val dateAdded =
                        cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED))

                    val contentUri: Uri = ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        id
                    )

                    images.add(GalleryImage(contentUri, name, dateAdded))
                }
            }

            // Imprime las URIs de imágenes cargadas
            images.forEach { galleryImage ->
                Log.d("GalleryViewModel", "Loaded image URI: ${galleryImage.uri}")
            }

            // Actualiza galleryImages con las imágenes obtenidas
            galleryImages.postValue(images)
        }
    }

    fun formatDate(dateInMillis: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return sdf.format(Date(dateInMillis * 1000)) // Multiplica por 1000 para convertir de segundos a milisegundos
    }
}

data class GalleryImage(
    val uri: Uri, // URI de la imagen
    val name: String, // Nombre de la imagen
    val dateAdded: Long // Fecha de captura de la imagen
)

