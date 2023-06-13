package com.example.eventapp.models.embedded.events.embedded

import com.example.eventapp.models.embedded.events.embedded.attractions.Attractions
import com.example.eventapp.models.embedded.events.embedded.venues.Venues
import com.google.gson.annotations.SerializedName


data class Embedded(

    @SerializedName("venues") var venues: ArrayList<Venues> = arrayListOf(),
    @SerializedName("attractions") var attractions: ArrayList<Attractions> = arrayListOf()

)