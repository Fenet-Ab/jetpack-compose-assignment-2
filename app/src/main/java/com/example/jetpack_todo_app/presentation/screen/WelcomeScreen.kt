package com.example.jetpack_todo_app.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun WelcomeScreen(navController: NavController){
    Column (
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFFE0E7FF)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ){
        Text(text="Welcome to the Todo App",
            style= TextStyle(
                fontSize = 24.sp,
                color= Color(0xFF345BD2)
            )
        )
        Spacer(modifier=Modifier.height(15.dp))
        Button(
            onClick = { navController.navigate("list") },

        ) {
            Text("Get Started")
        }


    }
}