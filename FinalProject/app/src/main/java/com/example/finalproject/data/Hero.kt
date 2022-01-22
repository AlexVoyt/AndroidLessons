package com.example.finalproject.data

import com.google.gson.annotations.SerializedName

data class Hero(
    @SerializedName("name") val name: String?,
    @SerializedName("class") val heroClass: String?,
    @SerializedName("specialty") val specialty: String?,
    @SerializedName("skill 1") val skill1: String?,
    @SerializedName("skill 2") val skill2: String?,
    @SerializedName("spell") val spell: String?)

