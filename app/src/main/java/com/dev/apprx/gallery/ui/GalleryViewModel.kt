package com.dev.apprx.gallery.ui


import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.inappmessaging.model.ImageData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class GalleryViewModel : ViewModel() {
    private val galleryImages: MutableLiveData<List<ImageData>> = MutableLiveData()

    // Devuelve LiveData que puede ser observado desde la UI
    fun getGalleryImages(): LiveData<List<ImageData>> {
        return galleryImages
    }

    // Carga las imágenes de la galería
    fun loadGalleryImages(contentResolver: ContentResolver) {
        viewModelScope.launch(Dispatchers.IO) {
            val folderPath = "App Rx"
            val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_ADDED
            )
            val selection = "${MediaStore.Images.Media.DATA} like '%$folderPath%'"
            val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"
            val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

            val images = mutableListOf<ImageData>()

            val cursor: Cursor? = contentResolver.query(
                uri,
                projection,
                selection,
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

                    val bitmap: Bitmap? = loadBitmapFromUri(contentUri, contentResolver)
                    images.add(ImageData(contentUri.toString(), bitmap))


                }
            }

            // Actualiza galleryImages con las imágenes obtenidas
            galleryImages.postValue(images)
        }
    }

    private fun loadBitmapFromUri(uri: Uri, contentResolver: ContentResolver): Bitmap? {
        return try {
            val inputStream = contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }



}