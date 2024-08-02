package com.artemklymenko.sneakersapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

@Composable
fun NestedAppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Routes.Feed.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){
        feedGraph(navController)
        favouritesGraph(navController)
        cartGraph(navController)
        profileGraph(navController)
    }
}

fun NavGraphBuilder.feedGraph(
    navController: NavHostController
){
    navigation(
        startDestination = Routes.Feed.route,
        route = Routes.FeedGraph.route
    ){
        composable(Routes.FeedGraph.route){
//            TODO: TBD
//            val viewModel = hiltViewModel<FeedViewModel>()
//            FeedScreen()
        }
        composable(Routes.Search.route){
//            TODO: TBD
//            val viewModel = hiltViewModel<SearchViewModel>()
//            SearchScreen()
        }
    }
}

fun NavGraphBuilder.favouritesGraph(
    navController: NavHostController
){
    navigation(
        startDestination = Routes.Favourites.route,
        route = Routes.FavouritesGraph.route
    ){
        composable(Routes.Favourites.route){
//            TODO: TBD
//            val viewModel = hiltViewModel<FeedViewModel>()
//            FeedScreen()
        }
    }
}

fun NavGraphBuilder.cartGraph(
    navController: NavHostController
){
    navigation(
        startDestination = Routes.Cart.route,
        route = Routes.CartGraph.route
    ){
        composable(Routes.Cart.route){
//            TODO: TBD
//            val viewModel = hiltViewModel<FeedViewModel>()
//            FeedScreen()
        }
    }
}

fun NavGraphBuilder.profileGraph(
    navController: NavHostController
){
    navigation(
        startDestination = Routes.Profile.route,
        route = Routes.ProfileGraph.route
    ){
        composable(Routes.Profile.route){
//            TODO: TBD
//            val viewModel = hiltViewModel<FeedViewModel>()
//            FeedScreen()
        }
    }
}