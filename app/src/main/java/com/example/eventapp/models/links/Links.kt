package com.example.eventapp.models.links

import com.example.eventapp.models.shared.Self
import com.google.gson.annotations.SerializedName


data class Links(

    @SerializedName("first") var first: First? = First(),
    @SerializedName("self") var self: Self? = Self(),
    @SerializedName("next") var next: Next? = Next(),
    @SerializedName("last") var last: Last? = Last()

)