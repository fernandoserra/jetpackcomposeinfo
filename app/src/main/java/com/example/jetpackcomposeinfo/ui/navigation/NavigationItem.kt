package com.example.jetpackcomposeinfo.ui.navigation

import com.example.jetpackcomposeinfo.R

sealed class NavigationItem(var route:String, var icon:Int,var title:String){
    object Home:NavigationItem("home", R.drawable.ic_home,"Home")
    object Favorites:NavigationItem("favorites", R.drawable.ic_favorites,"Favoritos")
}
