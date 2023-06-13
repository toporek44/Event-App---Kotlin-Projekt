package com.example.eventapp.models.embedded.events.ticketing

import com.google.gson.annotations.SerializedName


data class Ticketing(

    @SerializedName("safeTix") var safeTix: SafeTix? = SafeTix()

)