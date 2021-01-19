package com.example.catdigital_yardclub.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CategoryResult(
    @SerializedName("display_name")
    @Expose
    var displayName: String? = null,

    @SerializedName("results")
    @Expose
    var results: List<Results>? = null,

    @SerializedName("featured_photos")
    @Expose
    var featuredPhotos: List<FeaturedPhoto>? = null
)

data class Results(
    @SerializedName("id")
    @Expose
    var id: Int? = 0,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("description")
    @Expose
    var description: String? = null,

    @SerializedName("daily_rate")
    @Expose
    var dailyRate: String? = null,

    @SerializedName("weekly_rate")
    @Expose
    var weeklyRate: String? = null,

    @SerializedName("monthly_rate")
    @Expose
    var monthlyRate: String? = null,

    @SerializedName("operated_rates")
    @Expose
    var operatedRates: String? = null
)

data class FeaturedPhoto(
    @SerializedName("url")
    @Expose
    var url: String? = null
)