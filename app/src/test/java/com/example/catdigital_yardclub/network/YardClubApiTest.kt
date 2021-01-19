package com.example.catdigital_yardclub.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.catdigital_yardclub.BaseTest
import com.example.catdigital_yardclub.MockResponseFileReader
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class YardClubApiTest : BaseTest() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `read sample success json file`() {
        val reader = MockResponseFileReader("categoryResponse.json")
        Assert.assertNotNull(reader)
    }

    @Test
    fun `get Catalog result and check response Code 200 returned`() = runBlocking{
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("categoryResponse.json").content)
        mockWebServer.enqueue(response)

        val actualResponse = service.getCatalog().execute()
        Assert.assertEquals(
            response.toString().contains("200"),
            actualResponse.code().toString().contains("200"))
        Assert.assertNotNull(actualResponse)
        Assert.assertEquals(actualResponse.body()?.get(0)?.name, "Backhoes")
    }

    @Test
    fun `get Catalog and check response Code 400 returned`() = runBlocking {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
            .setBody(MockResponseFileReader("categoryResponse.json").content)
        mockWebServer.enqueue(response)

        val actualResponse = service.getCatalog().execute()
        Assert.assertEquals(
            response.toString().contains("400"),
            actualResponse.toString().contains("400")
        )
    }

    @Test
    fun `get Catalog Items Result and check response`() = runBlocking{
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("subcategoryResponse.json").content)
        mockWebServer.enqueue(response)
        val actualResponse = service.getCatalogItems(5).execute()
        Assert.assertNotNull(actualResponse)
        Assert.assertEquals(actualResponse.body()?.get(0)?.display_name, "D4 Dozer")
        Assert.assertEquals(actualResponse.body()?.get(0)?.detail, "D4 Dozers")
    }

    @Test
    fun `get Result Items Result and check response`() = runBlocking{
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("resultResponse.json").content)
        mockWebServer.enqueue(response)
        val actualResponse = service.getCategoryResult().execute()
        Assert.assertNotNull(actualResponse)
        Assert.assertEquals(actualResponse.body()?.displayName, "D8 Dozer")
        Assert.assertEquals(actualResponse.body()?.results?.get(0)?.name, "CAT D8N")
        Assert.assertEquals(actualResponse.body()?.results?.get(0)?.description, "Oper. Wt. 83K lbs")
        Assert.assertEquals(actualResponse.body()?.results?.get(0)?.dailyRate, "$1,525")
        Assert.assertEquals(actualResponse.body()?.results?.get(0)?.weeklyRate, "$4,575")
        Assert.assertEquals(actualResponse.body()?.results?.get(0)?.monthlyRate, "$13,700")
        Assert.assertEquals(actualResponse.body()?.results?.get(0)?.operatedRates, "")
    }
}