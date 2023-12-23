package com.example.multimediahub

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Categories.route)
    {
        composable(route = BottomBarScreen.Categories.route){
            CategoriesScreen()
        }
        composable(route = BottomBarScreen.Recents.route){
            RecentsScreen()
        }
    }
}