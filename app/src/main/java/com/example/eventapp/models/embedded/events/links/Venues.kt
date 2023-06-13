package com.example.eventapp.models.embedded.events.links

import com.google.gson.annotations.SerializedName


data class Venues(

    @SerializedName("href") var href: String? = null

)