package com.example.network

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("name") val name: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("fork") val fork: Boolean?,
    @SerializedName("created_at") val createdAt: String?)