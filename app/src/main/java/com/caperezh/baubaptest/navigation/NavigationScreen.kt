package com.caperezh.baubaptest.navigation

import android.annotation.SuppressLint

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.caperezh.baubaptest.common.SnackBarDemoAppState
import com.caperezh.baubaptest.common.rememberSnackBarDemoAppState
import com.caperezh.baubaptest.presentation.ui.LoginView
import com.caperezh.baubaptest.presentation.ui.SplashView


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationScreen() {
    val mySnackBar: SnackBarDemoAppState = rememberSnackBarDemoAppState()
    val navController = rememberNavController()
    Scaffold(
        content = {
            NavHost(
                navController = navController,
                startDestination = DestinationScreen.SplashScreenDest.route
            ) {
                composable(route = DestinationScreen.SplashScreenDest.route) {
                    SplashView(navController = navController)
                }

                composable(route = DestinationScreen.LoginScreenDest.route) {
                    LoginView(
                        showSnackBar = { message ->
                            mySnackBar.showSnackBar(message)
                        }
                    )
                }
            }
        },
        snackbarHost = {
            SnackbarHost(mySnackBar.snackBarState)
        },
    )
}
