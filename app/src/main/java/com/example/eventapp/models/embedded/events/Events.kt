package com.example.eventapp.models.embedded.events


import com.example.eventapp.models.embedded.events.dates.Dates
import com.example.eventapp.models.embedded.events.embedded.Embedded
import com.example.eventapp.models.embedded.events.links.Links
import com.example.eventapp.models.embedded.events.products.Products
import com.example.eventapp.models.embedded.events.sales.Sales
import com.example.eventapp.models.embedded.events.ticketing.Ticketing
import com.example.eventapp.models.shared.Classifications
import com.example.eventapp.models.shared.Images
import com.google.gson.annotations.SerializedName


data class Events(

    @SerializedName("name") var name: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("test") var test: Boolean? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("locale") var locale: String? = null,
    @SerializedName("images") var images: ArrayList<Images> = arrayListOf(),
    @SerializedName("sales") var sales: Sales? = Sales(),
    @SerializedName("dates") var dates: Dates? = Dates(),
    @SerializedName("classifications") var classifications: ArrayList<Classifications> = arrayListOf(),
    @SerializedName("promoter") var promoter: Promoter? = Promoter(),
    @SerializedName("promoters") var promoters: ArrayList<Promoters> = arrayListOf(),
    @SerializedName("pleaseNote") var pleaseNote: String? = null,
    @SerializedName("priceRanges") var priceRanges: ArrayList<PriceRanges> = arrayListOf(),
    @SerializedName("products") var products: ArrayList<Products> = arrayListOf(),
    @SerializedName("seatmap") var seatmap: Seatmap? = Seatmap(),
    @SerializedName("accessibility") var accessibility: Accessibility? = Accessibility(),
    @SerializedName("ticketLimit") var ticketLimit: TicketLimit? = TicketLimit(),
    @SerializedName("ageRestrictions") var ageRestrictions: AgeRestrictions? = AgeRestrictions(),
    @SerializedName("ticketing") var ticketing: Ticketing? = Ticketing(),
    @SerializedName("_links") var Links: Links? = Links(),
    @SerializedName("_embedded") var Embedded: Embedded? = Embedded()

)