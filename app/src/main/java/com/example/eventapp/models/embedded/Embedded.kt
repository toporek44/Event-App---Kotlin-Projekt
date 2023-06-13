package com.example.eventapp.models.embedded

import com.example.eventapp.models.embedded.events.Events
import com.google.gson.annotations.SerializedName


data class Embedded(

    @SerializedName("events") var events: ArrayList<Events> = arrayListOf()

)