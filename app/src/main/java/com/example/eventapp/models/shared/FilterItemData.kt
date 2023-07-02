package com.example.eventapp.models.shared

import com.google.gson.annotations.SerializedName

data class FilterItemData(
    @SerializedName("name") var name: String? = null,
    @SerializedName("value") var value: String? = null,
    @SerializedName("key") var key: String,

    )

