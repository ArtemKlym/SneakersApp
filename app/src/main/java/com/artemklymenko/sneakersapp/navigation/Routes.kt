package com.artemklymenko.sneakersapp.navigation

sealed class Routes(val route: String) {
    /**
     * Entry point
     */
    data object Splash: Routes("splash")

    /**
     * Welcome screen for users
     */
    data object Welcome: Routes("welcome")

    /**
     * Authentication
     */
    data object SignIn: Routes("sign_in")
    data object SignUp: Routes("sign_up")

    /**
     * Main screen: first page
     */
    data object Main: Routes("main")
    data object Feed: Routes("main/feed")
    data object Search : Routes("feed/search")
    data object Favourites: Routes("main/favourites")
    data object Cart: Routes("main/cart")
    data object Profile: Routes("main/profile")

    /**
     * Nested Graphs
     */
    data object FeedGraph : Routes("feed_graph")
    data object FavouritesGraph : Routes("favourites_graph")
    data object CartGraph : Routes("cart_graph")
    data object ProfileGraph : Routes("profile_graph")
}