package com.example.eventapp.models.embedded.events.dates

import com.google.gson.annotations.SerializedName

data class Dates(

    @SerializedName("start") var start: Start? = Start(),
    @SerializedName("timezone") var timezone: String? = null,
    @SerializedName("status") var status: Status? = Status(),
    @SerializedName("spanMultipleDays") var spanMultipleDays: Boolean? = null

)