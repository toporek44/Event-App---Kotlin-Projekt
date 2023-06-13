package com.example.eventapp.models.embedded.events.sales

import com.google.gson.annotations.SerializedName


data class Sales(

    @SerializedName("public") var public: Public? = Public(),
    @SerializedName("presales") var presales: ArrayList<Presales> = arrayListOf()

)