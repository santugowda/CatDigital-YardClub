package com.example.catdigital_yardclub

import com.example.catdigital_yardclub.network.YardClubApiServices
import io.mockk.MockKAnnotations
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseTest {
    lateinit var service: YardClubApiServices
    lateinit var mockWebServer: MockWebServer


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mockWebServer = MockWebServer()
        mockWebServer.start()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YardClubApiServices::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }


    protected fun mockAnnotations(aRelaxUnitFun: Boolean = true)
    {
        MockKAnnotations.init(this, relaxUnitFun = aRelaxUnitFun)
    }
}