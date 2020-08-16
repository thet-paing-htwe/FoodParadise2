package com.tphtwe.foodparadise.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tphtwe.foodparadise.api.ApiClient
import com.tphtwe.foodparadise.model.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelCategory :ViewModel(){

   private var resultCategory:MutableLiveData<Category> = MutableLiveData()

    fun getResultCategory():LiveData<Category> = resultCategory

    fun loadResultCategory(){
        var apiClient=ApiClient()
        var call=apiClient.getCategories()
        call.enqueue(object :Callback<Category>{
            override fun onFailure(call: Call<Category>, t: Throwable) {
                Log.d("fail", t.toString())
            }

            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                resultCategory.value=response!!.body()

            }

        })

    }

}