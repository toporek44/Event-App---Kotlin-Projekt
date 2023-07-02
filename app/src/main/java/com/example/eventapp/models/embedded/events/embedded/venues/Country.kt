package com.example.eventapp.models.embedded.events.embedded.venues

import com.google.gson.annotations.SerializedName


data class Country(
    var expanded: Boolean = false,
    @SerializedName("name") var name: String? = null,
    @SerializedName("countryCode") var countryCode: String? = null

)