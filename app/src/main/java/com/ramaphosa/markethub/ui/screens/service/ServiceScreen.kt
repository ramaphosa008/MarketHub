package com.ramaphosa.markethub.ui.screens.service

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ramaphosa.markethub.ui.screens.navigation.ROUT_ONBOARDING
import kotlinx.coroutines.delay

@Composable
fun ServiceScreen(navController: NavController){

    Column(
        modifier = Modifier.fillMaxSize()
    ) { }

}

@Preview(showBackground = true)
@Composable
fun ServiceScreenPreview() {

    ServiceScreen(rememberNavController())

}
