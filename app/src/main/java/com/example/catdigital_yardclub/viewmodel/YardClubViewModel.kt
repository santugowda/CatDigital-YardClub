package com.example.catdigital_yardclub.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catdigital_yardclub.model.Catalog
import com.example.catdigital_yardclub.model.CatalogItems
import com.example.catdigital_yardclub.model.CategoryResult
import com.example.catdigital_yardclub.network.NetworkResponse
import com.example.catdigital_yardclub.repository.YardClubRepository

class YardClubViewModel : ViewModel() {

    val TAG = YardClubViewModel::class.java.simpleName
    var yardClubRepository = YardClubRepository()

    private var catalogResponse : MutableLiveData<NetworkResponse<List<Catalog>>> = MutableLiveData()
    private var catalogItemResponse : MutableLiveData<NetworkResponse<List<CatalogItems>>> = MutableLiveData()
    private var categoryResultResponse : MutableLiveData<NetworkResponse<CategoryResult>> = MutableLiveData()

    fun catalogNames(): MutableLiveData<NetworkResponse<List<Catalog>>> {
        catalogResponse = yardClubRepository.getCatalog()
        Log.d(TAG, "viewmodel get catalog list  called")
        return catalogResponse
    }

    fun catalogItems(id : Int) : MutableLiveData<NetworkResponse<List<CatalogItems>>>{
        catalogItemResponse = yardClubRepository.getCatalogItems(id)
        Log.d(TAG, "viewmodel get catalog items  called")
        return catalogItemResponse
    }

    fun categoryResult(): MutableLiveData<NetworkResponse<CategoryResult>>{
        categoryResultResponse = yardClubRepository.getCategoryResult()
        Log.d(TAG, "viewmodel get category Result items  called")
        return categoryResultResponse
    }
}
