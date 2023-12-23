package com.example.multimediahub

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier
        .fillMaxSize()
        //.background(Color.Black)
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SearchScreen()
            SwitchButton()
        }

        Column(
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                ButtonItem("Image",R.drawable.image4)
                Spacer(modifier = Modifier.width(10.dp))
                ButtonItem("Video",R.drawable.video2)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                ButtonItem("Music",R.drawable.music1)
                Spacer(modifier = Modifier.width(10.dp))
                ButtonItem("PDF",R.drawable.pdf1)
            }
        }

        Spacer(modifier = Modifier.height(200.dp))



    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(){
    SearchBar(query = "",
        onQueryChange = {},
        onSearch ={} ,
        placeholder = {
            Text(text = "Search Files")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = null
            )
        },
        trailingIcon = {},
        active = false ,
        onActiveChange ={},
        tonalElevation = 0.dp,
        content = {},
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .padding(top = 0.dp, start = 4.dp, end = 4.dp, bottom = 4.dp)
    )

}

@Composable
fun SwitchButton(){
    var checked by remember { mutableStateOf(false) }
    Switch(
        checked = checked,
        onCheckedChange = {checked = it},
        thumbContent = if (checked) {
            {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize),
                )
            }
        } else {
            {
                Icon(
                    imageVector = Icons.Rounded.Done,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize),
                )
            }
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = MaterialTheme.colorScheme.primary,
            checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
            uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
            uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
    )
}

@Composable
fun ButtonItem(text:String,res:Int){
    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        modifier = Modifier
            .padding(0.dp)
            .size(130.dp)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = res),
                contentDescription = "Images",
                modifier = Modifier
                    .size(80.dp)
                    .padding(0.dp)
            )
            Text(text = text, color = Color.Gray, fontSize = 16.sp)
        }
    }
}





@Preview
@Composable
fun MultimediaHub(){
    MainScreen()
}