package com.example.jetpack_todo_app.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpack_todo_app.data.model.Todo
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(
    todoJson: String?,
    navController: NavController
) {
    val todo = Gson().fromJson(todoJson, Todo::class.java)
    Scaffold(
        containerColor = Color(0xFFE0E7FF),
        topBar = {

            TopAppBar(

                title = { Text("Todo Details",
                    style= TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,


                    ),
                    modifier=Modifier.padding(start = 50.dp)

                ) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",

                        )
                    }
                }

            )
        }
    ) { padding ->
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(top=200.dp, start = 60.dp,end=40.dp),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier=Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Here is the details of todo",
                    color = Color.Black,
                    style=TextStyle(
                        fontSize = 20.sp,

                        )

                )



            Card (
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color(0xFF648EF5))


                ){

                Column(
                    modifier = Modifier
                        .background(Color(0xFF648EF5))
                        .padding(16.dp)
                        ,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Title: ${todo.title}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Completed: ${if (todo.completed) "Yes" else "No"}")
                    Text(text = "User ID: ${todo.userId}")
                    Text(text = "Todo ID: ${todo.id}")
                }
            }
        }
        }


    }
}
