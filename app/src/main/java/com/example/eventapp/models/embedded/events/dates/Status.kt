package com.example.eventapp.models.embedded.events.dates

import com.google.gson.annotations.SerializedName

data class Status(

    @SerializedName("code") var code: String? = null

)