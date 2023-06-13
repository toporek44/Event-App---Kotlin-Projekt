package com.example.eventapp.models.shared

import com.google.gson.annotations.SerializedName


data class Images(

    @SerializedName("ratio") var ratio: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("width") var width: Int? = null,
    @SerializedName("height") var height: Int? = null,
    @SerializedName("fallback") var fallback: Boolean? = null

)