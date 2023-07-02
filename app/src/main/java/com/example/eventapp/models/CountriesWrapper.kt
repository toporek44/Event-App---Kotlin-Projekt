package com.example.eventapp.models

import com.example.eventapp.models.embedded.events.embedded.venues.Country
import com.google.gson.annotations.SerializedName


data class CountriesWrapper(

    @SerializedName("countries") var countries: ArrayList<Country> = arrayListOf(),

    )

