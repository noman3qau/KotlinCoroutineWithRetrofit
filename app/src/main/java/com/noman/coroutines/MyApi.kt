package com.noman.coroutines

import com.noman.coroutines.model.Comment
import retrofit2.Response
import retrofit2.http.GET

interface MyApi {

    @GET("comments")
    suspend fun getComments(): Response<List<Comment>>
}