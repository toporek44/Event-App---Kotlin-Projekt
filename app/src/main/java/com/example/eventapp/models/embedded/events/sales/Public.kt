package com.example.eventapp.models.embedded.events.sales

import com.google.gson.annotations.SerializedName

data class Public (

    @SerializedName("startDateTime")
    var startDateTime: String? = null,
    @SerializedName("startTBD")
    var startTBD: Boolean? = null,
    @SerializedName("startTBA")
    var startTBA: Boolean? = null,
    @SerializedName("endDateTime")
    var endDateTime: String? = null

)