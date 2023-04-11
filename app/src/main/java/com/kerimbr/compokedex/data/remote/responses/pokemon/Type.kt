package com.kerimbr.compokedex.data.remote.responses.pokemon


import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("slot")
    val slot: Int?,
    @SerializedName("type")
    val type: TypeX?
)