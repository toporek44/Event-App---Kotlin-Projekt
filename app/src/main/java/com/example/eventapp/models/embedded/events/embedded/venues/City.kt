package com.example.eventapp.models.embedded.events.embedded.venues

import com.google.gson.annotations.SerializedName


data class City(

    @SerializedName("name") var name: String? = null

)