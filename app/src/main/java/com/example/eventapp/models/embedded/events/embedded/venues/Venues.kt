package com.example.eventapp.models.embedded.events.embedded.venues


import com.example.eventapp.models.embedded.events.embedded.venues.links.Links
import com.example.eventapp.models.shared.Images
import com.google.gson.annotations.SerializedName


data class Venues(

    @SerializedName("name") var name: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("test") var test: Boolean? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("locale") var locale: String? = null,
    @SerializedName("aliases") var aliases: ArrayList<String> = arrayListOf(),
    @SerializedName("images") var images: ArrayList<Images> = arrayListOf(),
    @SerializedName("postalCode") var postalCode: String? = null,
    @SerializedName("timezone") var timezone: String? = null,
    @SerializedName("city") var city: City? = City(),
    @SerializedName("state") var state: State? = State(),
    @SerializedName("country") var country: Country? = Country(),
    @SerializedName("address") var address: Address? = Address(),
    @SerializedName("location") var location: Location? = Location(),
    @SerializedName("markets") var markets: ArrayList<Markets> = arrayListOf(),
    @SerializedName("dmas") var dmas: ArrayList<Dmas> = arrayListOf(),
    @SerializedName("boxOfficeInfo") var boxOfficeInfo: BoxOfficeInfo? = BoxOfficeInfo(),
    @SerializedName("parkingDetail") var parkingDetail: String? = null,
    @SerializedName("accessibleSeatingDetail") var accessibleSeatingDetail: String? = null,
    @SerializedName("generalInfo") var generalInfo: GeneralInfo? = GeneralInfo(),
    @SerializedName("upcomingEvents") var upcomingEvents: UpcomingEvents? = UpcomingEvents(),
    @SerializedName("_links") var Links: Links? = Links()

)