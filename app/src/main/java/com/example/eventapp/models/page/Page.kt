package com.example.eventapp.models.page

import com.google.gson.annotations.SerializedName


data class Page(

    @SerializedName("size") var size: Int? = null,
    @SerializedName("totalElements") var totalElements: Int? = null,
    @SerializedName("totalPages") var totalPages: Int? = null,
    @SerializedName("number") var number: Int? = null

)