package com.example.eventapp.models

import com.example.eventapp.models.embedded.Embedded
import com.example.eventapp.models.links.Links
import com.example.eventapp.models.page.Page
import com.google.gson.annotations.SerializedName


data class EventsWrapper(

    @SerializedName("_embedded") var Embedded: Embedded? = Embedded(),
    @SerializedName("_links") var Links: Links? = Links(),
    @SerializedName("page") var page: Page? = Page()

)

