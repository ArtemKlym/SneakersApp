package com.artemklymenko.sneakersapp.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.artemklymenko.sneakersapp.navigation.Routes
import com.artemklymenko.sneakersapp.pages.main.MainScreen
import com.artemklymenko.sneakersapp.pages.main.MainViewModel
import com.artemklymenko.sneakersapp.pages.sign_in.SignInScreen
import com.artemklymenko.sneakersapp.pages.sign_in.SignInViewModel
import com.artemklymenko.sneakersapp.pages.sign_up.SignUpScreen
import com.artemklymenko.sneakersapp.pages.sign_up.SignUpViewModel
import com.artemklymenko.sneakersapp.pages.splash.SplashScreen
import com.artemklymenko.sneakersapp.pages.splash.SplashViewModel
import com.artemklymenko.sneakersapp.pages.welcome.WelcomeScreen
import com.artemklymenko.sneakersapp.pages.welcome.WelcomeViewModel
import com.artemklymenko.sneakersapp.ui.theme.SneakersAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SneakersAppTheme {
                RootAppNavigation()
            }
        }
    }
}

@Composable
fun RootAppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.Splash.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.Splash.route){
            val viewModel = hiltViewModel<SplashViewModel>()
            SplashScreen(
                viewModel = viewModel,
                onNavigationNext = {
                    navController.navigate(route = Routes.Welcome.route){
                        popUpTo(0)
                    }
                }
            )
        }
        composable(Routes.Welcome.route){
            val viewModel = hiltViewModel<WelcomeViewModel>()
            WelcomeScreen(
                viewModel = viewModel,
                onNavigationNext = {
                    navController.navigate(route = Routes.Welcome.route){
                        popUpTo(0)
                    }
                }
            )
        }
        composable(Routes.SignIn.route){
            val viewModel = hiltViewModel<SignInViewModel>()
            SignInScreen(
                viewModel = viewModel,
                onNavigationNext = {
                    navController.navigate(route = Routes.Welcome.route){
                        popUpTo(0)
                    }
                }
            )
        }
        composable(Routes.SignUp.route){
            val viewModel = hiltViewModel<SignUpViewModel>()
            SignUpScreen(
                viewModel = viewModel,
                onNavigationNext = {
                    navController.navigate(route = Routes.Welcome.route){
                        popUpTo(0)
                    }
                }
            )
        }
        composable(Routes.Main.route){
            val viewModel = hiltViewModel<MainViewModel>()
            MainScreen(
                viewModel = viewModel,
                onNavigationNext = {
                    navController.navigate(route = Routes.Welcome.route){
                        popUpTo(0)
                    }
                }
            )
        }
    }
}