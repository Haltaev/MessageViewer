package com.example.messageviewer.data.api

import com.example.messageviewer.data.api.model.BaseResponse
import com.example.messageviewer.data.api.model.Message
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getMessageList(): List<Message>
}