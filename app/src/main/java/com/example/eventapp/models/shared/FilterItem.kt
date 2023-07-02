package com.example.eventapp.models.shared

import com.google.gson.annotations.SerializedName

data class FilterItem(
    @SerializedName("name") var name: String? = null,
    @SerializedName("data") var data: MutableList<FilterItemData>? = null,
    @SerializedName("expanded") var expanded: Boolean = false
)

