package com.example.jetpack_todo_app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetpack_todo_app.data.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(todos: List<Todo>)

    @Query("SELECT * FROM todos")
    fun getAllTodos(): Flow<List<Todo>>
}