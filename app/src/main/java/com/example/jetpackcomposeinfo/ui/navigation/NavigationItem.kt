package com.example.jetpackcomposeinfo.ui.navigation

import com.example.jetpackcomposeinfo.R

/**
 * @Author: Fernando Serra
 */

sealed class NavigationItem(var route:String, var icon:Int,var title:String){
    object Home:NavigationItem("home", R.drawable.ic_home,"Home")
    object Details:NavigationItem("details", R.drawable.ic_home,"Details")
    object Favorites:NavigationItem("favorites", R.drawable.ic_favorites,"Favoritos")
    object Info:NavigationItem("info", R.drawable.ic_info,"Info")
}
