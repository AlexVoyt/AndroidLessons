package com.example.network

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar_url") val avatarURL: String?,
    @SerializedName("login") val login: String?,
    @SerializedName("id") val id: Int?)
