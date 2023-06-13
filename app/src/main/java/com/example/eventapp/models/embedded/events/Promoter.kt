package com.example.eventapp.models.embedded.events


import com.google.gson.annotations.SerializedName


data class Promoter(

    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("description") var description: String? = null

)