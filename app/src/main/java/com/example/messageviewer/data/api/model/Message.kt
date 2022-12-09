package com.example.messageviewer.data.api.model

import com.google.gson.annotations.SerializedName

class Message(
    @SerializedName("id")
    val id: Long,
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,

    var removed: Boolean = false,
)