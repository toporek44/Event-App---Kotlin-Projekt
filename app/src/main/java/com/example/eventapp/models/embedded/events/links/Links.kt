package com.example.eventapp.models.embedded.events.links

import com.example.eventapp.models.shared.Self
import com.google.gson.annotations.SerializedName


data class Links(

    @SerializedName("self") var self: Self? = Self(),
    @SerializedName("attractions") var attractions: ArrayList<Attractions> = arrayListOf(),
    @SerializedName("venues") var venues: ArrayList<Venues> = arrayListOf()

)