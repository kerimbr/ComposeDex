package com.kerimbr.compokedex.data.remote.responses.pokemon


import com.google.gson.annotations.SerializedName

data class İtem(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)