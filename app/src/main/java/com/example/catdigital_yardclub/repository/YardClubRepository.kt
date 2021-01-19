package com.example.catdigital_yardclub.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.catdigital_yardclub.model.Catalog
import com.example.catdigital_yardclub.model.CatalogItems
import com.example.catdigital_yardclub.model.CategoryResult
import com.example.catdigital_yardclub.network.NetworkResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YardClubRepository : BaseRepository() {
    private val TAG = YardClubRepository::class.java.simpleName
    private val catalogResponse = MutableLiveData<NetworkResponse<List<Catalog>>>()
    private val catalogItemResponse = MutableLiveData<NetworkResponse<List<CatalogItems>>>()
    private val categoryResultResponse = MutableLiveData<NetworkResponse<CategoryResult>>()

    fun getCatalog() : MutableLiveData<NetworkResponse<List<Catalog>>> {
        yardClubService.getCatalog().enqueue(object : Callback<List<Catalog>> {
            override fun onResponse(call: Call<List<Catalog>>, response: Response<List<Catalog>>) {
                if(response.isSuccessful){
                    Log.d(TAG, "Retrofit call successful with response " + response.body().toString())
                    catalogResponse.postValue(NetworkResponse.success(response.body()))
                }
            }

            override fun onFailure(call: Call<List<Catalog>>, t: Throwable) {
                Log.d(TAG, "Retrofit call failure with error " + t.localizedMessage)
                catalogResponse.postValue(NetworkResponse.failure(t.localizedMessage, null))
            }
        })
        return catalogResponse
    }

    fun getCatalogItems(id: Int) : MutableLiveData<NetworkResponse<List<CatalogItems>>>{
        yardClubService.getCatalogItems(id).enqueue(object : Callback<List<CatalogItems>> {
            override fun onResponse(call: Call<List<CatalogItems>>, response: Response<List<CatalogItems>>) {
                if(response.isSuccessful){
                    Log.d(TAG, "Retrofit call successful with response " + response.body().toString())
                    catalogItemResponse.postValue(NetworkResponse.success(response.body()))
                }
            }

            override fun onFailure(call: Call<List<CatalogItems>>, t: Throwable) {
                Log.d(TAG, "Retrofit call failure with error " + t.localizedMessage)
                catalogItemResponse.postValue(NetworkResponse.failure(t.localizedMessage, null))
            }
        })
        return catalogItemResponse
    }

    fun getCategoryResult() : MutableLiveData<NetworkResponse<CategoryResult>>{
        yardClubService.getCategoryResult().enqueue(object : Callback<CategoryResult> {
            override fun onResponse(call: Call<CategoryResult>, response: Response<CategoryResult>) {
                if(response.isSuccessful){
                    Log.d(TAG, "Retrofit call successful with response " + response.body().toString())
                    categoryResultResponse.postValue(NetworkResponse.success(response.body()))
                }
            }

            override fun onFailure(call: Call<CategoryResult>, t: Throwable) {
                Log.d(TAG, "Retrofit call failure with error " + t.localizedMessage)
                categoryResultResponse.postValue(NetworkResponse.failure(t.localizedMessage, null))
            }
        })
        return categoryResultResponse
    }
}