package com.example.jetpack_todo_app.network

import com.example.jetpack_todo_app.data.model.Todo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface TodoApiService {
    @GET("todos")
    suspend fun getTodos(): List<Todo>

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/" // Replace with your actual base URL

        fun getInstance(): TodoApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TodoApiService::class.java)
        }
    }
}