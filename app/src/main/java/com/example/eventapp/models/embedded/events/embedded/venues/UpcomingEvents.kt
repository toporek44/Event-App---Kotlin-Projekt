package com.example.eventapp.models.embedded.events.embedded.venues

import com.google.gson.annotations.SerializedName


data class UpcomingEvents(

    @SerializedName("ticketmaster") var ticketmaster: Int? = null,
    @SerializedName("_total") var Total: Int? = null,
    @SerializedName("_filtered") var Filtered: Int? = null

)