package com.example.eventapp.models.embedded.events.embedded.attractions.UpcomingEvents


import com.google.gson.annotations.SerializedName


data class UpcomingEvents(

    @SerializedName("tmr") var tmr: Int? = null,
    @SerializedName("ticketmaster") var ticketmaster: Int? = null,
    @SerializedName("_total") var Total: Int? = null,
    @SerializedName("_filtered") var Filtered: Int? = null

)