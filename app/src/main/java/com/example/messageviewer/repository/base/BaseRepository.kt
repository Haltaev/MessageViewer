package com.example.messageviewer.repository.base

import android.accounts.NetworkErrorException
import com.example.messageviewer.R
import com.example.messageviewer.data.api.model.BaseResponse

open class BaseRepository {
    suspend fun <T> getBaseResponse(apiAction: suspend () -> T): BaseResponse<T> {
        val response: BaseResponse<T> = try {
            val messages = apiAction.invoke()
            BaseResponse(messages)
        } catch (e: ClassCastException) {
            BaseResponse(null, R.string.error_class_cast)
        } catch (e: NetworkErrorException) {
            BaseResponse(null, R.string.error_network_error)
        } catch (e: Exception) {
            BaseResponse(null)
        }
        return response
    }
}