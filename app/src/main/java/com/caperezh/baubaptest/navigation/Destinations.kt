package com.caperezh.baubaptest.navigation

sealed class DestinationScreen(val route: String) {
    object SplashScreenDest : DestinationScreen(route =  "splash_screen")
    object LoginScreenDest : DestinationScreen(route = "login_screen")
    object HomeScreenDest : DestinationScreen(route = "home_screen")
}