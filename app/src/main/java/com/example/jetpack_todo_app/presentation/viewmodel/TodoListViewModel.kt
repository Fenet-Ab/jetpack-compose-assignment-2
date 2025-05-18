package com.example.jetpack_todo_app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_todo_app.data.model.Todo
import com.example.jetpack_todo_app.data.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

sealed class TodoUiState {
    object Loading : TodoUiState()
    data class Success(val todos: List<Todo>) : TodoUiState()
    data class Error(val message: String) : TodoUiState()
}

class TodoListViewModel(private val repository: TodoRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<TodoUiState>(TodoUiState.Loading)
    val uiState: StateFlow<TodoUiState> = _uiState

    init {
        loadTodos()
    }

    fun loadTodos() {
        viewModelScope.launch {
            repository.todos
                .onStart {
                    _uiState.value = TodoUiState.Loading
                    repository.refreshTodos()
                }
                .catch {
                    _uiState.value = TodoUiState.Error("Failed to load todos.")
                }
                .collect { todos ->
                    _uiState.value = TodoUiState.Success(todos)
                }
        }
    }
}
