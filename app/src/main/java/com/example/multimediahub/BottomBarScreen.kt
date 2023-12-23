package com.example.multimediahub

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen (
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Categories: BottomBarScreen(
        route = "categories",
        title = "Categories",
        icon = Icons.Default.Home
    )
    object Recents: BottomBarScreen(
        route = "recents",
        title = "Recents",
        icon = Icons.Default.List
    )
}