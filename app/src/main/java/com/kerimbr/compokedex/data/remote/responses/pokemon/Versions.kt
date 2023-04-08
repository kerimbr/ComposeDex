package com.kerimbr.compokedex.data.remote.responses.pokemon


import com.google.gson.annotations.SerializedName

data class Versions(
    @SerializedName("generation-v")
    val generationV: GenerationV?,
    @SerializedName("generation-vi")
    val generationVi: GenerationVi?,
    @SerializedName("generation-vii")
    val generationVii: GenerationVii?,
    @SerializedName("generation-viii")
    val generationViii: GenerationViii?,
    @SerializedName("generation-i")
    val generationI: GenerationI?,
    @SerializedName("generation-ii")
    val generationIi: GenerationIi?,
    @SerializedName("generation-iii")
    val generationIii: GenerationIii?,
    @SerializedName("generation-iv")
    val generationIv: GenerationIv?
)