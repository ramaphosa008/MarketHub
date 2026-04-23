package com.ramaphosa.markethub.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ramaphosa.markethub.R
import com.ramaphosa.markethub.data.AuthViewModel
import com.ramaphosa.markethub.ui.screens.navigation.ROUT_HOME
import com.ramaphosa.markethub.ui.theme.green8

@Composable
fun LoginScreen(navController: NavController){

    Column(
        modifier = Modifier
            .paint( painter = painterResource(R.drawable.img_5), contentScale = ContentScale.FillBounds)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(R.drawable.img),
            contentDescription = "img",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = " Welcome Back! ",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
        )

        Spacer(modifier = Modifier.width(10.dp))




        //Variables
        var email by remember { mutableStateOf("") }

        var password by remember { mutableStateOf("") }

        OutlinedTextField(
            value = email,
            onValueChange = { email = it},
            modifier = Modifier.width(350.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "") },
            label = {Text(text = "Email Address")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = green8,
                focusedTextColor = Color.Black,
                unfocusedLeadingIconColor = green8
            )

        )

        Spacer(modifier = Modifier.width(20.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it},
            modifier = Modifier.width(350.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "") },
            label = {Text(text = "Password")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = green8,
                focusedTextColor = Color.Black,
                unfocusedLeadingIconColor = green8
            ),
            visualTransformation = PasswordVisualTransformation()

        )

        Spacer(modifier = Modifier.width(20.dp))


        // Login Button
        val context = LocalContext.current
        val authViewModel = AuthViewModel(navController, context)
        Button(
            onClick = {

                authViewModel.login(email, password)

                      },
            colors =  ButtonDefaults.buttonColors(green8),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.width(350.dp)

        ) {
            Text( text = "Login")        }







        Spacer(modifier = Modifier.width(20.dp))

        Button(
            onClick = {navController.navigate(ROUT_HOME)},
            colors = ButtonDefaults.buttonColors(green8),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.width(350.dp)
        ) {
            Text(text = "Go to Home")
        }


        TextButton( onClick = {}) {

            Text( text = "Don't have an account? Register")
        }


    }

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {

    LoginScreen(rememberNavController())

}