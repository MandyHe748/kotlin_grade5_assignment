//package com.example.todo.network
//
//import com.example.todo.model.Todo
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.GET
//
//interface Api {
//    @GET("todos")
//    suspend fun getTodos(): List<Todo>
//
//    companion object {
//        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
//
//        private var todosService: Api? = null
//
//        fun getInstance(): Api {
//            if (todosService == null) {
//                todosService = Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                    .create(Api::class.java)
//            }
//            return todosService!!
//        }
//    }
//}
package com.example.moviesearch.network

import com.example.moviesearch.appdata.MovieResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("discover/movie")
    suspend fun getDiscoverMovies(
        @Query("api_key") apiKey: String,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US"
    ): MovieResponse

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"


        val instance: Api by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }
}