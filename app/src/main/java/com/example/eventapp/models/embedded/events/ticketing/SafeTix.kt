package com.example.eventapp.models.embedded.events.ticketing

import com.google.gson.annotations.SerializedName


data class SafeTix(

    @SerializedName("enabled") var enabled: Boolean? = null

)
