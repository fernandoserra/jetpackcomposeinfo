package com.example.jetpackcomposeinfo.ui.navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeinfo.ShowList
import com.example.jetpackcomposeinfo.presentation.DataViewModel
import com.example.jetpackcomposeinfo.ui.favorites.FavoriteView
import com.example.jetpackcomposeinfo.ui.info.Info

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Favorites,
        NavigationItem.Info
    )
    BottomNavigation(
        //backgroundColor = colorResource(id = R.color.design_default_color_primary),
        contentColor = Color.White,
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                //selected = false,
                selected = currentRoute== item.route,
                onClick = {
                    /* Add code later */
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationComponent(navController: NavHostController, viewModel: DataViewModel) {

    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            ShowList(viewModel)
        }
        composable(NavigationItem.Favorites.route) {
            FavoriteView()
        }
        composable(NavigationItem.Info.route) {
            Info()
        }
    }
}

@Composable
fun MainScreen(viewModel: DataViewModel) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {  }, //TopBar()
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        NavigationComponent(navController,viewModel)
    }
}