package com.tphtwe.foodparadise.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tphtwe.foodparadise.api.ApiClient
import com.tphtwe.foodparadise.model.Area.Area
import com.tphtwe.foodparadise.model.country.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelMealByArea :ViewModel(){
    private val areaMeal: MutableLiveData<Area> = MutableLiveData()

    fun getareaMeal(): LiveData<Area> = areaMeal

    fun loadMeal(country:String){
        var apiClient= ApiClient()
        var call=apiClient.getMealByAreas(country)
        call.enqueue(object : Callback<Area>{
            override fun onFailure(call: Call<Area>, t: Throwable) {

            }

            override fun onResponse(call: Call<Area>, response: Response<Area>) {
                areaMeal.value=response.body()!!
            }

        })
    }

}