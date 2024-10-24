package com.artemklymenko.sneakersapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.artemklymenko.sneakersapp.domain.models.network.auth.User
import com.artemklymenko.sneakersapp.pages.main.pages.cart.CartScreen
import com.artemklymenko.sneakersapp.pages.main.pages.cart.CartViewModel
import com.artemklymenko.sneakersapp.pages.main.pages.favourites.FavouritesScreen
import com.artemklymenko.sneakersapp.pages.main.pages.favourites.FavouritesViewModel
import com.artemklymenko.sneakersapp.pages.main.pages.feed.FeedScreen
import com.artemklymenko.sneakersapp.pages.main.pages.feed.FeedViewModel
import com.artemklymenko.sneakersapp.pages.main.pages.profile.ProfileScreen
import com.artemklymenko.sneakersapp.pages.main.pages.profile.ProfileViewModel
import com.artemklymenko.sneakersapp.pages.main.pages.profile.address.DeliveryAddressScreen
import com.artemklymenko.sneakersapp.pages.main.pages.profile.address.DeliveryAddressViewModel
import com.artemklymenko.sneakersapp.pages.main.pages.profile.billing.BillingDetailsScreen
import com.artemklymenko.sneakersapp.pages.main.pages.profile.billing.BillingDetailsViewModel
import com.artemklymenko.sneakersapp.pages.main.pages.profile.personal.PersonalDetailsScreen
import com.artemklymenko.sneakersapp.pages.main.pages.profile.personal.PersonalDetailsViewModel
import com.artemklymenko.sneakersapp.pages.main.pages.profile.settings.SettingsScreen

@Composable
fun NestedAppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Routes.FeedGraph.route,
    isDarkTheme: Boolean,
    onNavigateToProduct: (Int) -> Unit,
    onNavigateToNotifications: () -> Unit,
    onChangeFavourite: (Long) -> Unit,
    onNavigateToPromoCode: () -> Unit,
    onNavigateToCheckout: () -> Unit,
    onNavigateToSignIn: () -> Unit,
    onThemeChange: () -> Unit,
    user: User
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        feedGraph(
            navController = navController,
            onNavigateToProduct = onNavigateToProduct,
            onNavigateToNotifications = onNavigateToNotifications
        )

        favouritesGraph(
            navController = navController,
            onNavigateToProduct = onNavigateToProduct,
            onChangeFavourite = onChangeFavourite
        )

        cartGraph(
            navController = navController,
            onNavigateToPromoCode = onNavigateToPromoCode,
            onNavigateToCheckout = onNavigateToCheckout,
            onNavigateToProduct = onNavigateToProduct,
        )

        profileGraph(
            navController = navController,
            user = user,
            onNavigateToSignIn = onNavigateToSignIn,
            isDarkTheme = isDarkTheme,
            onThemeChange = onThemeChange
        )
    }
}

fun NavGraphBuilder.feedGraph(
    navController: NavHostController,
    onNavigateToNotifications: () -> Unit,
    onNavigateToProduct: (Int) -> Unit
) {
    navigation(
        startDestination = Routes.Feed.route,
        route = Routes.FeedGraph.route
    ) {
        composable(Routes.Feed.route) {
            val viewModel = hiltViewModel<FeedViewModel>()
            FeedScreen(
                viewModel = viewModel,
                onNavigateToNotifications = onNavigateToNotifications,
                onNavigateToProduct = onNavigateToProduct
            )
        }
    }
}

fun NavGraphBuilder.favouritesGraph(
    navController: NavHostController,
    onNavigateToProduct: (Int) -> Unit,
    onChangeFavourite: (Long) -> Unit
) {
    navigation(
        startDestination = Routes.Favourites.route,
        route = Routes.FavouritesGraph.route
    ) {
        composable(Routes.Favourites.route) {
            val viewModel = hiltViewModel<FavouritesViewModel>()
            FavouritesScreen(
                viewModel = viewModel,
                onNavigateToProduct = onNavigateToProduct
            )
        }
    }
}

fun NavGraphBuilder.cartGraph(
    navController: NavHostController,
    onNavigateToPromoCode: () -> Unit,
    onNavigateToCheckout: () -> Unit,
    onNavigateToProduct: (Int) -> Unit,
) {
    navigation(
        startDestination = Routes.Cart.route,
        route = Routes.CartGraph.route
    ) {
        composable(Routes.Cart.route) {
            val viewModel = hiltViewModel<CartViewModel>()
            CartScreen(
                viewModel = viewModel,
                onNavigateToPromoCode = onNavigateToPromoCode,
                onNavigateToCheckout = onNavigateToCheckout,
                onNavigateToProduct = onNavigateToProduct,
            )
        }
    }
}

fun NavGraphBuilder.profileGraph(
    navController: NavHostController,
    onNavigateToSignIn: () -> Unit,
    isDarkTheme: Boolean,
    onThemeChange: () -> Unit,
    user: User
) {
    navigation(
        startDestination = Routes.Profile.route,
        route = Routes.ProfileGraph.route
    ) {
        composable(Routes.Profile.route) {
            val viewModel = hiltViewModel<ProfileViewModel>()
            ProfileScreen(
                viewModel = viewModel,
                user = user,
                onNavigateToSettings = {
                    navController.navigate(Routes.Settings.route)
                },
                onNavigateToPersonalDetails = {
                    navController.navigate(Routes.Personal.route)
                },
                onNavigateToDeliveryAddress = {
                    navController.navigate(Routes.Address.route)
                },
                onNavigateToBillingDetails = {
                    navController.navigate(Routes.Billing.route)
                },
                onNavigateToLogOut = {
                    onNavigateToSignIn.invoke()
                })
        }

        composable(Routes.Settings.route) {
            SettingsScreen(
                onBackClick = { navController.popBackStack() },
                isDarkTheme = isDarkTheme,
                onThemeChange = onThemeChange
            )
        }

        composable(Routes.Personal.route) {
            val viewModel = hiltViewModel<PersonalDetailsViewModel>()
            PersonalDetailsScreen(viewModel = viewModel) {
                navController.popBackStack()
            }
        }

        composable(Routes.Address.route) {
            val viewModel = hiltViewModel<DeliveryAddressViewModel>()
            DeliveryAddressScreen(viewModel = viewModel) {
                navController.popBackStack()
            }
        }

        composable(Routes.Billing.route) {
            val viewModel = hiltViewModel<BillingDetailsViewModel>()
            BillingDetailsScreen(viewModel = viewModel) {
                navController.popBackStack()
            }
        }
    }
}