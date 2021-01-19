package com.example.catdigital_yardclub.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CatalogItems (
    @SerializedName("id")
    @Expose
    var catalogId: String? = null,

    @SerializedName("detail")
    @Expose
    var detail: String? = null,

    @SerializedName("display_name")
    @Expose
    var display_name: String? = null
)