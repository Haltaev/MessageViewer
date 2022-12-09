package com.example.messageviewer.repository.messages

import com.example.messageviewer.data.api.ApiService
import com.example.messageviewer.data.api.model.BaseResponse
import com.example.messageviewer.data.api.model.Message
import com.example.messageviewer.repository.base.BaseRepository
import javax.inject.Inject

class MessagesRepositoryImpl @Inject constructor(private val api: ApiService) : BaseRepository(),
    MessagesRepository {

    override suspend fun getMessages(): BaseResponse<List<Message>> {
        return getBaseResponse { api.getMessageList() }
    }
}