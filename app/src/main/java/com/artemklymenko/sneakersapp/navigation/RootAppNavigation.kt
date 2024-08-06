package com.artemklymenko.sneakersapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.artemklymenko.sneakersapp.pages.confirm.ConfirmScreen
import com.artemklymenko.sneakersapp.pages.confirm.ConfirmViewModel
import com.artemklymenko.sneakersapp.pages.main.MainScreen
import com.artemklymenko.sneakersapp.pages.notifications.NotificationsScreen
import com.artemklymenko.sneakersapp.pages.notifications.NotificationsViewModel
import com.artemklymenko.sneakersapp.pages.product.ProductScreen
import com.artemklymenko.sneakersapp.pages.product.ProductViewModel
import com.artemklymenko.sneakersapp.pages.sign_in.SignInScreen
import com.artemklymenko.sneakersapp.pages.sign_in.SignInViewModel
import com.artemklymenko.sneakersapp.pages.sign_up.SignUpScreen
import com.artemklymenko.sneakersapp.pages.sign_up.SignUpViewModel
import com.artemklymenko.sneakersapp.pages.splash.SplashScreen
import com.artemklymenko.sneakersapp.pages.splash.SplashViewModel
import com.artemklymenko.sneakersapp.pages.success.SuccessScreen
import com.artemklymenko.sneakersapp.pages.success.SuccessViewModel
import com.artemklymenko.sneakersapp.pages.welcome.WelcomeScreen
import com.artemklymenko.sneakersapp.pages.welcome.WelcomeViewModel

@Composable
fun RootAppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.Splash.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        route = null
    ) {
        composable(Routes.Splash.route) {
            val viewModel = hiltViewModel<SplashViewModel>()
            SplashScreen(
                viewModel = viewModel,
                onNavigationNext = {
                    navController.navigate(route = Routes.Welcome.route) {
                        popUpTo(0)
                    }
                }
            )
        }
        composable(Routes.Welcome.route) {
            val viewModel = hiltViewModel<WelcomeViewModel>()
            WelcomeScreen(
                viewModel = viewModel,
                onNavigationToSignIn = {
                    navController.navigate(route = Routes.SignIn.route)
                },
                onNavigationToSignUp = {
                    navController.navigate(route = Routes.SignUp.route)
                }
            )
        }
        composable(Routes.SignIn.route) {
            val viewModel = hiltViewModel<SignInViewModel>()
            SignInScreen(
                viewModel = viewModel,
                onBackClick = {
                    navController.navigate(route = Routes.Welcome.route) {
                        popUpTo(0)
                    }
                },
                onForgotPasswordClick = {
                    //TODO: TBD.
                },
                onLoginClick = {
                    navController.navigate(route = Routes.Main.route) {
                        popUpTo(0)
                    }
                },
                onLoginGoogleClick = {
                    //TODO: TBD.
                },
                onRegisterClick = {
                    navController.navigate(route = Routes.SignUp.route) {
                        popUpTo(0)
                    }
                },
            )
        }
        composable(Routes.SignUp.route) {
            val viewModel = hiltViewModel<SignUpViewModel>()
            SignUpScreen(
                viewModel = viewModel,
                onBackClick = {
                    navController.navigate(route = Routes.Welcome.route) {
                        popUpTo(0)
                    }
                },
                onNextClick = {
                    navController.navigate(route = Routes.Main.route) {
                        popUpTo(0)
                    }
                },
                onTermsClick = {
                    //TODO: TBD.
                },
                onPrivacyClick = {
                    //TODO: TBD.
                },
            )
        }
        composable(Routes.Main.route) {
            MainScreen(
                onNavigateToProduct = {
                    navController.navigate(route = Routes.Product.getProductById("null"))
                },
                onNavigateToNotifications = {
                    navController.navigate(route = Routes.Notifications.route)
                },
                onNavigateToConfirmation = {
                    navController.navigate(route = Routes.Confirmation.route)
                },
                onChangeFavourite = {}
            )
        }

        composable(Routes.Product.route){
            val viewModel = hiltViewModel<ProductViewModel>()
            ProductScreen(viewModel = viewModel) {
                navController.popBackStack()
            }
        }

        composable(Routes.Confirmation.route){
            val viewModel = hiltViewModel<ConfirmViewModel>()
            ConfirmScreen(
                viewModel = viewModel,
                onNavigateToSuccess = {
                    navController.navigate(Routes.Success.route)
                }) {
                navController.popBackStack()
            }
        }

        composable(Routes.Success.route){
            val viewModel = hiltViewModel<SuccessViewModel>()
            SuccessScreen(viewModel = viewModel) {
                navController.navigate(Routes.Main.route)
            }
        }

        composable(Routes.Notifications.route){
            val viewModel = hiltViewModel<NotificationsViewModel>()
            NotificationsScreen(viewModel = viewModel) {
                navController.popBackStack()
            }
        }
    }
}