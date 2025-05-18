package com.example.jetpack_todo_app.data.repository

import android.content.Context
import com.example.jetpack_todo_app.data.local.TodoDao
import com.example.jetpack_todo_app.data.local.TodoDatabase
import com.example.jetpack_todo_app.data.model.Todo
import com.example.jetpack_todo_app.network.TodoApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class TodoRepository(
    private val api: TodoApiService,
    private val dao: TodoDao
) {
    val todos: Flow<List<Todo>> = dao.getAllTodos()

    suspend fun refreshTodos() {
        try {
            val remoteTodos = api.getTodos()
            dao.insertAll(remoteTodos)
        } catch (e: Exception) {
            // Optional: log or handle error
        }
    }

    suspend fun getTodoById(id: Int): Todo? {
        return dao.getAllTodos().firstOrNull()?.find { it.id == id }
    }

    companion object {
        @Volatile
        private var INSTANCE: TodoRepository? = null

        fun getInstance(context: Context): TodoRepository {
            return INSTANCE ?: synchronized(this) {
                val database = TodoDatabase.getDatabase(context)
                val api = TodoApiService.getInstance()
                val instance = TodoRepository(api, database.todoDao())
                INSTANCE = instance
                instance
            }
        }
    }
}