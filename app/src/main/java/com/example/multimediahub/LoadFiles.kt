import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoadFiles(){
var pickedImageUri by remember { mutableStateOf<Uri?>(null) }
val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
    println("selected file URI ${it.data?.data}")
    pickedImageUri = it.data?.data
}
pickedImageUri?.let {
    Text(it.toString())
}
Button(
onClick = {
    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        .apply {
            addCategory(Intent.CATEGORY_OPENABLE)
        }
    launcher.launch(intent)
}
) {
    Text("Select")
}
}

@Preview
@Composable
fun prevfun(){
    LoadFiles()
}