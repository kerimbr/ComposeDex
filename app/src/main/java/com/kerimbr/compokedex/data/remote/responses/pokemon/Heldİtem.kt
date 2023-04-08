package com.kerimbr.compokedex.data.remote.responses.pokemon


import com.google.gson.annotations.SerializedName

data class Heldİtem(
    @SerializedName("item")
    val item: İtem?,
    @SerializedName("version_details")
    val versionDetails: List<VersionDetail>?
)