package com.example.eventapp.models.embedded.events

import com.google.gson.annotations.SerializedName


data class TicketLimit(

    @SerializedName("info") var info: String? = null

)