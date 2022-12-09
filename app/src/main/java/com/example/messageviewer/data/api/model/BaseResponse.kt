package com.example.messageviewer.data.api.model

import com.example.messageviewer.R

data class BaseResponse<T>(
    val data: T?,
    private val errorMessageId: Int? = null
) {
    fun isSuccess(): Boolean = data != null

    fun getErrorMessage(): Int = errorMessageId ?: R.string.error_unknown
}