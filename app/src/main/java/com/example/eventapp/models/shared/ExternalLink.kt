package com.example.eventapp.models.shared

import com.google.gson.annotations.SerializedName


data class ExternalLink (

    @SerializedName("url" ) var url : String? = null

)