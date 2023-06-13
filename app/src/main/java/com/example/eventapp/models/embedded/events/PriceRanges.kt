package com.example.eventapp.models.embedded.events


import com.google.gson.annotations.SerializedName


data class PriceRanges(

    @SerializedName("type") var type: String? = null,
    @SerializedName("currency") var currency: String? = null,
    @SerializedName("min") var min: Double? = null,
    @SerializedName("max") var max: Double? = null

)