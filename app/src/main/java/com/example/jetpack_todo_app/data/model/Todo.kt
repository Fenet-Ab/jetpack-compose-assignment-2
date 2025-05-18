package com.example.jetpack_todo_app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "todos")
data class Todo(
    @SerializedName("userId")
    val userId: Int,

    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("completed")
    val completed: Boolean
)