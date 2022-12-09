package com.example.messageviewer.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messageviewer.data.api.model.Message
import com.example.messageviewer.repository.messages.MessagesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val messagesRepository: MessagesRepository) :
    ViewModel() {
    private var messageCounter = 0
    private var messages: MutableList<Message> = mutableListOf()

    private var _messagesLiveData = MutableLiveData<List<Message>>()
    val messagesLiveData: LiveData<List<Message>> = _messagesLiveData

    private var _errorLiveData = MutableLiveData<Int>()
    val errorLiveData: LiveData<Int> = _errorLiveData

    fun loadAllMessages() {
        viewModelScope.launch {
            val response = messagesRepository.getMessages()
            if (response.isSuccess()) {
                messageCounter = 0
                messages.clear()
                messages.addAll(response.data ?: listOf())
                _messagesLiveData.postValue(messages)
            } else {
                _errorLiveData.postValue(response.getErrorMessage())
            }
        }
    }

    fun markNewMessageAsRemoved(position: Int) {
        messages.removeAt(messages.indexOfFirst { it.removed })
        messages[position].removed = true
        _messagesLiveData.postValue(messages)
    }

    fun removeMessage(position: Int) {
        messages.removeAt(position)
    }
}