package com.artemklymenko.sneakersapp.navigation

sealed class Routes(val route: String) {

    companion object {
        const val PRODUCT_ID = "product_id"
        const val PAYMENT_ID = "payment_id"
        const val ADDRESS_ID = "address_id"
    }

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
    /**
     * Main screen: first page
     */
    data object Feed : Routes("feed")
    data object Product : Routes("feed/{$PRODUCT_ID}") {
        fun getProductById(id: Long): String {
            return "feed/$id"
        }
    }

    // MainScreen: second page
    data object Favourites: Routes("favourites")

    //MainScreen: third page
    data object Cart: Routes("cart")
    data object Promo : Routes("cart/promo")
    data object Checkout : Routes("cart/checkout")
    data object Confirmation : Routes("cart/checkout/confirmation/{$PAYMENT_ID}/{$ADDRESS_ID}"){
        fun getPaymentAndAddressById(paymentId: Long, addressId: Long): String{
            return "cart/checkout/confirmation/$paymentId/$addressId"
        }
    }

    //MainScreen: fourth page
    data object Profile: Routes("profile")
    data object Settings: Routes("profile/settings")
    data object Personal: Routes("profile/personal")
    data object Address: Routes("profile/address")
    data object Billing: Routes("profile/billing")

    /**
     * Nested Graphs
     */
    data object FeedGraph : Routes("feed_graph")
    data object FavouritesGraph : Routes("favourites_graph")
    data object CartGraph : Routes("cart_graph")
    data object ProfileGraph : Routes("profile_graph")


    /**
     * Independent single screens
     */
    data object Notifications : Routes("notifications")
    data object Success : Routes("success")
}