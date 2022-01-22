package com.example.finalproject.data

import com.google.gson.annotations.SerializedName

data class Creature(
    @SerializedName("name") val name: String?,
    @SerializedName("lvl") val lvl: Int?,
    @SerializedName("att") val att: Int?,
    @SerializedName("def") val def: Int?,
    @SerializedName("min_d") val minD: Int?,
    @SerializedName("max_d") val maxD: Int?,
    @SerializedName("hp") val hp: Int?,
    @SerializedName("spd") val spd: Int?,
    @SerializedName("grw") val grw: Int?,
    @SerializedName("val") val value: Int?,
    @SerializedName("special") val special: String?
    )