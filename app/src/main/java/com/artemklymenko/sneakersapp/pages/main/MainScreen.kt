package com.artemklymenko.sneakersapp.pages.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.artemklymenko.sneakersapp.R
import com.artemklymenko.sneakersapp.navigation.NestedAppNavigation
import com.artemklymenko.sneakersapp.navigation.Routes

@Composable
fun MainScreen(
    onNavigateToProduct: (Long) -> Unit,
    onNavigateToNotifications: () -> Unit,
    onNavigateToConfirmation: () -> Unit,
    onChangeFavourite: (Long) -> Unit
) {
    val navController = rememberNavController()
    val navGraphs: List<Routes> = listOf(
        Routes.Feed,
        Routes.Favourites,
        Routes.Cart,
        Routes.Profile,
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                navGraphs.forEach { graph ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.
                        any { it.route == graph.route } == true,
                        onClick = {
                            navController.navigate(graph.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                contentDescription = null,
                                imageVector = when (graph) {
                                    Routes.Feed -> Icons.Filled.Home
                                    Routes.Favourites -> Icons.Filled.Favorite
                                    Routes.Cart -> Icons.Filled.ShoppingCart
                                    Routes.Profile -> Icons.Filled.Person
                                    else -> Icons.Filled.Home
                                }
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(
                                    id = when (graph) {
                                        Routes.Feed -> R.string.label_feed
                                        Routes.Favourites -> R.string.label_favourites
                                        Routes.Cart -> R.string.label_cart
                                        Routes.Profile -> R.string.label_profile
                                        else -> R.string.label_feed
                                    }
                                )
                            )
                        }
                    )
                }
            }

        }
    ) {
        NestedAppNavigation(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding()),
            navController = navController,
            onNavigateToProduct = onNavigateToProduct,
            onNavigateToNotifications = onNavigateToNotifications,
            onNavigateToConfirmation = onNavigateToConfirmation,
            onChangeFavourite = onChangeFavourite,
        )
    }
}