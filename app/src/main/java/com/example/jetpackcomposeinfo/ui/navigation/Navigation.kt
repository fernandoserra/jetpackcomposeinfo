package com.example.jetpackcomposeinfo.ui.navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.jetpackcomposeinfo.ShowList
import com.example.jetpackcomposeinfo.data.model.Team
import com.example.jetpackcomposeinfo.presentation.DataViewModel
import com.example.jetpackcomposeinfo.ui.details.DetailsTeam
import com.example.jetpackcomposeinfo.ui.favorites.FavoriteView
import com.example.jetpackcomposeinfo.ui.info.Info
import com.google.gson.Gson

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

@ExperimentalMaterialApi
@Composable
fun NavigationComponent(navController: NavHostController, viewModel: DataViewModel) {

    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            ShowList(viewModel,navController)
        }

        composable("${NavigationItem.Details.route}/{id}" ,
            arguments = listOf(navArgument("id") { type = NavType.StringType })) { backStackEntry ->
            backStackEntry.arguments?.getString("id")?.let { json ->
                val team = Gson().fromJson(json, Team::class.java)
                DetailsTeam(team,viewModel,navController)
            }
        }

        composable(NavigationItem.Favorites.route) {
            FavoriteView(viewModel,navController)
        }
        composable(NavigationItem.Info.route) {
            Info()
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun MainScreen(viewModel: DataViewModel) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    Scaffold(
        topBar = {  }, //TopBar()
        bottomBar = {
            if (currentRoute != "details/{id}") {
                BottomNavigationBar(navController)
            }
        }
    ) {
        NavigationComponent(navController,viewModel)
    }
}