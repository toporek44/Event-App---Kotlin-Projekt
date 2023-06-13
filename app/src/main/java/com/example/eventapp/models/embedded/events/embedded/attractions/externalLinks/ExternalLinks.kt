package com.example.eventapp.models.embedded.events.embedded.attractions.externalLinks


import com.example.eventapp.models.shared.ExternalLink
import com.google.gson.annotations.SerializedName


data class ExternalLinks(

    @SerializedName("youtube") var youtube: ArrayList<ExternalLinks> = arrayListOf(),
    @SerializedName("twitter") var twitter: ArrayList<ExternalLink> = arrayListOf(),
    @SerializedName("itunes") var itunes: ArrayList<ExternalLink> = arrayListOf(),
    @SerializedName("lastfm") var lastfm: ArrayList<ExternalLink> = arrayListOf(),
    @SerializedName("facebook") var facebook: ArrayList<ExternalLink> = arrayListOf(),
    @SerializedName("wiki") var wiki: ArrayList<ExternalLink> = arrayListOf(),
    @SerializedName("spotify") var spotify: ArrayList<ExternalLink> = arrayListOf(),
    @SerializedName("musicbrainz") var musicbrainz: ArrayList<Musicbrainz> = arrayListOf(),
    @SerializedName("instagram") var instagram: ArrayList<ExternalLink> = arrayListOf(),
    @SerializedName("homepage") var homepage: ArrayList<ExternalLink> = arrayListOf()

)