package com.example.eventapp.models.shared

import com.google.gson.annotations.SerializedName


data class Genre(
    var expanded: Boolean = false,
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null

)