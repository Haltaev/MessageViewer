package com.example.messageviewer.repository.messages

import com.example.messageviewer.data.api.model.BaseResponse
import com.example.messageviewer.data.api.model.Message

interface MessagesRepository {
    suspend fun getMessages(): BaseResponse<List<Message>>
}