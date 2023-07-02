package com.example.eventapp.models

import com.example.eventapp.models.shared.Genre
import com.google.gson.annotations.SerializedName


data class GenresWrapper(
    @SerializedName("genres") var genres: ArrayList<Genre> = arrayListOf(),
)

