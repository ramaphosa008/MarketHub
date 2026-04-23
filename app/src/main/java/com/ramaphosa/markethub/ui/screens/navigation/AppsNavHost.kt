package com.ramaphosa.markethub.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ramaphosa.markethub.ui.screens.about.AboutScreen
import com.ramaphosa.markethub.ui.screens.auth.LoginScreen
import com.ramaphosa.markethub.ui.screens.auth.RegisterScreen
import com.ramaphosa.markethub.ui.screens.home.HomeScreen
import com.ramaphosa.markethub.ui.screens.intent.IntentScreen
import com.ramaphosa.markethub.ui.screens.onboarding.OnboardingScreen
import com.ramaphosa.markethub.ui.screens.payments.PaymentScreen
import com.ramaphosa.markethub.ui.screens.products.AddProductScreen
import com.ramaphosa.markethub.ui.screens.products.UpdateProductScreen
import com.ramaphosa.markethub.ui.screens.products.ViewProductScreen
import com.ramaphosa.markethub.ui.screens.scaffold.ScaffoldScreen
import com.ramaphosa.markethub.ui.screens.service.ServiceScreen
import com.ramaphosa.markethub.ui.screens.splash.SplashScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }

        composable(ROUT_ABOUT) {
            AboutScreen(navController)
        }

        composable(ROUT_LOGIN) {
            LoginScreen(navController)
        }

        composable(ROUT_REGISTER) {
            RegisterScreen(navController)
        }

        composable(ROUT_ONBOARDING) {
            OnboardingScreen(navController)
        }

        composable(ROUT_PAYMENT) {
            PaymentScreen(navController)
        }

        composable(ROUT_SERVICE) {
            ServiceScreen(navController)
        }

        composable(ROUT_INTENT) {
            IntentScreen(navController)
        }

        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }

        composable(ROUT_SCAFFOLD) {
            ScaffoldScreen(navController)
        }


        composable(ROUTE_ADD_PRODUCT) { AddProductScreen(navController) }

        composable(ROUTE_VIEW_PRODUCTS) { ViewProductScreen(navController) }

        composable(
            ROUTE_UPDATE_PRODUCT,
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")!!
            UpdateProductScreen(navController, productId)
        }




    }

}