package com.example.eventapp.models.shared

import com.google.gson.annotations.SerializedName


data class Classifications(

    @SerializedName("primary") var primary: Boolean? = null,
    @SerializedName("segment") var segment: Segment? = Segment(),
    @SerializedName("genre") var genre: Genre? = Genre(),
    @SerializedName("subGenre") var subGenre: SubGenre? = SubGenre(),
    @SerializedName("type") var type: Type? = Type(),
    @SerializedName("subType") var subType: SubType? = SubType(),
    @SerializedName("family") var family: Boolean? = null

)