package com.example.eventapp.models.embedded.events.embedded.venues.links


import com.example.eventapp.models.shared.Self
import com.google.gson.annotations.SerializedName


data class Links(

    @SerializedName("self") var self: Self? = Self()

)