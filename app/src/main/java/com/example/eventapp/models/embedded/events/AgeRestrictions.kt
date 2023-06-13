package com.example.eventapp.models.embedded.events

import com.google.gson.annotations.SerializedName


data class AgeRestrictions (

    @SerializedName("legalAgeEnforced" ) var legalAgeEnforced : Boolean? = null

)