package com.example.jetpack_todo_app.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.jetpack_todo_app.data.model.Todo
import com.example.jetpack_todo_app.presentation.viewmodel.TodoListViewModel
import com.example.jetpack_todo_app.presentation.viewmodel.TodoListViewModelFactory
import com.example.jetpack_todo_app.presentation.viewmodel.TodoUiState
import com.example.jetpack_todo_app.data.repository.TodoRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    repository: TodoRepository,
    navController: NavController,
    onItemClick: (Todo) -> Unit
) {
    val viewModel: TodoListViewModel = viewModel(
        factory = TodoListViewModelFactory(repository)
    )
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Todos") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->                     // ⬅️ forward Scaffold insets
        when (uiState) {
            is TodoUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .padding(padding)  // ← respect topBar height
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is TodoUiState.Error -> {
                val message = (uiState as TodoUiState.Error).message
                Text(
                    text = "Error: $message",
                    modifier = Modifier
                        .padding(padding)   // same
                        .padding(16.dp),
                    color = MaterialTheme.colorScheme.error
                )
            }

            is TodoUiState.Success -> {
                val todoList = (uiState as TodoUiState.Success).todos
                LazyColumn(
                    modifier = Modifier
                        .padding(padding)   // same
                        .fillMaxSize()
                        .background(Color(0xFFE0E7FF))
                ) {
                    items(todoList, key = { it.id }) { todo ->
                        TodoListItem(todo = todo) { onItemClick(todo) }
                    }
                }
            }
        }
    }
}

@Composable
fun TodoListItem(todo: Todo, onClick: () -> Unit) {
    Card(
        colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = todo.title, style = MaterialTheme.typography.titleMedium,
                color = Color.Black )
            Text(text = if (todo.completed) "Completed" else "Not Completed",
                color = Color.Gray)
        }
    }
}

