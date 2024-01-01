package com.example.multimediahub

import android.Manifest
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.multimediahub.ui.theme.MultimediaHubTheme

class MainActivity2 : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultimediaHubTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    var images by remember { mutableStateOf<List<Uri>>(emptyList()) }
                    val permissionLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.RequestPermission(),
                        onResult = { isGranted ->
                            if (isGranted) {
                                images = loadImagesFromStorage(context)
                            } else {
                                // Handle permission denial
                            }
                        }
                    )

                    LaunchedEffect(key1 = true) {
                        permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                    }
                    ListScreen(images)
                }
            }
        }
    }
}

@Composable
fun ImageItem(imageUri: Uri) {
    AsyncImage(
        model = imageUri,
        contentDescription = null
    )
}

fun loadImagesFromStorage(context: Context): List<Uri> {
    val imageList = mutableListOf<Uri>()
    val projection = arrayOf(MediaStore.Images.Media._ID)
    val cursor = context.contentResolver.query(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        projection,
        null,
        null,
        null
    )

    cursor?.use {
        val idColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
        while (it.moveToNext()) {
            val id = it.getLong(idColumn)
            val contentUri: Uri = ContentUris.withAppendedId(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                id
            )
            imageList.add(contentUri)
        }
    }
    return imageList
}

@Composable
fun ListScreen(images: List<Uri>){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(images) {
            imageUri -> ImageCard("Image1",imageUri,27/12/2023,11.00,10)
        /*items(images) { imageUri ->
            ImageItem(imageUri)*/
        }
    }
}