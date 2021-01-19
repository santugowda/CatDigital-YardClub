package com.example.catdigital_yardclub.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Catalog(
    @SerializedName("id")
    @Expose
    var id: Int? = 0,

    @SerializedName("name")
    @Expose
    var name: String? = null
)