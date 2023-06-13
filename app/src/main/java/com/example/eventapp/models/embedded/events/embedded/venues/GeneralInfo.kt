package com.example.eventapp.models.embedded.events.embedded.venues

import com.google.gson.annotations.SerializedName


data class GeneralInfo(

    @SerializedName("generalRule") var generalRule: String? = null,
    @SerializedName("childRule") var childRule: String? = null

)