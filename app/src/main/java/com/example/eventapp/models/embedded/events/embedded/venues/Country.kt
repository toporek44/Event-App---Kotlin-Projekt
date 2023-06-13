package com.example.eventapp.models.embedded.events.embedded.venues

import com.google.gson.annotations.SerializedName


data class Country(

    @SerializedName("name") var name: String? = null,
    @SerializedName("countryCode") var countryCode: String? = null

)