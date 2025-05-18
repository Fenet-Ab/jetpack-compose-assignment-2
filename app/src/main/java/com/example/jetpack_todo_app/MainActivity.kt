package com.example.jetpack_todo_app

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpack_todo_app.presentation.screen.TodoDetailScreen
import com.example.jetpack_todo_app.presentation.screen.TodoListScreen
import com.example.jetpack_todo_app.data.repository.TodoRepository
import com.example.jetpack_todo_app.presentation.screen.WelcomeScreen
import com.example.jetpack_todo_app.ui.theme.Jetpack_Todo_AppTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = TodoRepository.getInstance(applicationContext)
        enableEdgeToEdge()
        setContent {
            Jetpack_Todo_AppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "welcome"
                    ) {
                        composable("welcome") {
                            WelcomeScreen(navController)
                        }
                        composable("list") {
                            TodoListScreen(
                                repository = repository,
                                navController = navController,
                                onItemClick = { todo ->
                                    val todoJson = Uri.encode(Gson().toJson(todo))
                                    navController.navigate("detail/$todoJson")
                                }
                            )
                        }


                        composable(
                            route = "detail/{todo}",
                            arguments = listOf(
                                navArgument("todo") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val todoJson = backStackEntry.arguments?.getString("todo")
                            TodoDetailScreen(todoJson = todoJson, navController = navController)
                        }
                    }
                }
            }
        }} }