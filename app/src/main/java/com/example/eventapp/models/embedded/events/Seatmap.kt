package com.example.eventapp.models.embedded.events

import com.google.gson.annotations.SerializedName


data class Seatmap(

    @SerializedName("staticUrl") var staticUrl: String? = null

)