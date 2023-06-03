package com.example.vrg_soft_test.screens.post_details

import android.app.Application
import android.content.ContentValues
import android.os.Environment
import android.provider.MediaStore
import android.webkit.URLUtil
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import kotlin.coroutines.CoroutineContext

class PostDetailsViewModel(private val application: Application) : AndroidViewModel(application),
    CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main


    fun saveImage(imageUrl: String) = launch {
        withContext(Dispatchers.IO) {
            val url = URL(imageUrl)
            val imageName = URLUtil.guessFileName(imageUrl, null, null)
            val values = ContentValues()
            values.put(MediaStore.Images.Media.DISPLAY_NAME, imageName)
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            application.contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values
            )?.let { uri ->
                application.contentResolver.openOutputStream(uri)
                    ?.use { output ->
                        url.openStream()
                            ?.use { input ->
                                val buffer = ByteArray(1024)
                                var bytesRead: Int
                                while (input.read(buffer, 0, buffer.size)
                                        .also { bytesRead = it } >= 0
                                ) {
                                    output.write(buffer, 0, bytesRead)
                                }
                            }
                    }
            }
        }
    }
}