package com.example.eventapp.models.links


import com.google.gson.annotations.SerializedName


data class Next(

    @SerializedName("href") var href: String? = null

)