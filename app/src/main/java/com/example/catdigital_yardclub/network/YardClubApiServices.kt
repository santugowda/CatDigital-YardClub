package com.example.catdigital_yardclub.network

import com.example.catdigital_yardclub.model.Catalog
import com.example.catdigital_yardclub.model.CatalogItems
import com.example.catdigital_yardclub.model.CategoryResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface YardClubApiServices {
    @GET("catalog.json")
    fun getCatalog():
            Call<List<Catalog>>

    @GET("catalog/{id}.json")
    fun getCatalogItems(
        @Path("id") id: Int,
    ): Call<List<CatalogItems>>

    @GET("results.json")
    fun getCategoryResult():
            Call<CategoryResult>

}