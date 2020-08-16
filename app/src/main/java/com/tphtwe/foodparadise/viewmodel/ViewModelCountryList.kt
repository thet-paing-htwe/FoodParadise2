package com.tphtwe.foodparadise.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tphtwe.foodparadise.api.ApiClient
import com.tphtwe.foodparadise.model.country.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelCountryList :ViewModel(){
    private val listCountry:MutableLiveData<Country> = MutableLiveData()

    fun getListCountry(): LiveData<Country> = listCountry

    fun loadListCountry(area:String){
        var apiClient=ApiClient()
        var call=apiClient.getCountryListItems(area)
        call.enqueue(object :Callback<Country>{
            override fun onFailure(call: Call<Country>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<Country>, response: Response<Country>) {
                listCountry.value=response.body()!!
            }

        })


    }

}