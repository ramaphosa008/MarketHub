package com.ramaphosa.markethub.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ramaphosa.markethub.R

@Composable
fun OnboardingScreen(navController: NavController){

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {


        Image(
            painter = painterResource(R.drawable.shoe),
            contentDescription = "shoe",
            modifier = Modifier.size(300.dp)
            )

        Text(
            text = "WELCOME TO MARKETHUB",
            fontSize = 30.sp
        )

    }

}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {

    OnboardingScreen(rememberNavController())

}