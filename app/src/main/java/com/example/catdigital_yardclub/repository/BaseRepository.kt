package com.example.catdigital_yardclub.repository

import com.example.catdigital_yardclub.network.RetrofitClient
import com.example.catdigital_yardclub.network.YardClubApiServices


open class BaseRepository {

    open val yardClubService = RetrofitClient.getClient(YardClubApiServices::class.java)
}