package com.example.eventapp.models.embedded.events.embedded.venues

import com.google.gson.annotations.SerializedName


data class Address(

    @SerializedName("line1") var line1: String? = null

)