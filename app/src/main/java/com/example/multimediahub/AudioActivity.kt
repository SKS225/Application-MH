package com.example.multimediahub

import android.Manifest
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import com.example.multimediahub.ui.theme.MultimediaHubTheme

class MainActivity4 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultimediaHubTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    var audios by remember { mutableStateOf<List<Uri>>(emptyList()) }
                    val permissionLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.RequestPermission(),
                        onResult = { isGranted ->
                            if (isGranted) {
                                audios = loadAudiosFromStorage(context)
                            } else {
                                // Handle permission denial
                            }
                        }
                    )

                    LaunchedEffect(key1 = true) {
                        permissionLauncher.launch(Manifest.permission.READ_MEDIA_AUDIO)
                    }

                    LazyColumn {
                        items(audios) { videoUri ->
                            AudioItem(videoUri)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AudioItem(audioUri: Uri) {
    AsyncImage(
        model = audioUri,
        contentDescription = null
    )
}

fun loadAudiosFromStorage(context: Context): List<Uri> {
    val audioList = mutableListOf<Uri>()
    val projection = arrayOf(MediaStore.Audio.Media._ID)
    val cursor = context.contentResolver.query(
        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
        projection,
        null,
        null,
        null
    )

    cursor?.use {
        val idColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
        while (it.moveToNext()) {
            val id = it.getLong(idColumn)
            val contentUri: Uri = ContentUris.withAppendedId(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                id
            )
            audioList.add(contentUri)
        }
    }
    return audioList
}
