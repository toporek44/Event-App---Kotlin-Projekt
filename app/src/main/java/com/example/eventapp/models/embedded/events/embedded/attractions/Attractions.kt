package com.example.eventapp.models.embedded.events.embedded.attractions

import com.example.eventapp.models.embedded.events.embedded.attractions.Links.Links
import com.example.eventapp.models.embedded.events.embedded.attractions.UpcomingEvents.UpcomingEvents
import com.example.eventapp.models.shared.Classifications
import com.example.eventapp.models.embedded.events.embedded.attractions.externalLinks.ExternalLinks
import com.example.eventapp.models.shared.Images
import com.google.gson.annotations.SerializedName


data class Attractions(

    @SerializedName("name") var name: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("test") var test: Boolean? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("locale") var locale: String? = null,
    @SerializedName("externalLinks") var externalLinks: ExternalLinks? = ExternalLinks(),
    @SerializedName("aliases") var aliases: ArrayList<String> = arrayListOf(),
    @SerializedName("images") var images: ArrayList<Images> = arrayListOf(),
    @SerializedName("classifications") var classifications: ArrayList<Classifications> = arrayListOf(),
    @SerializedName("upcomingEvents") var upcomingEvents: UpcomingEvents? = UpcomingEvents(),
    @SerializedName("_links") var Links: Links? = Links()

)