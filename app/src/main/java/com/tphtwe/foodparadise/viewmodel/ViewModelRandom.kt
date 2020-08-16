package com.tphtwe.foodparadise.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tphtwe.foodparadise.api.ApiClient
import com.tphtwe.foodparadise.model.Category
import com.tphtwe.foodparadise.model.Random
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class ViewModelRandom : ViewModel(){
    private var resultRandom: MutableLiveData<Random> = MutableLiveData()

    fun getResultRandom(): LiveData<Random> = resultRandom

    fun loadResultRandom(){
        var apiClient= ApiClient()
        var call=apiClient.getRandoms()
        call.enqueue(object : Callback<Random> {
            override fun onFailure(call: Call<Random>, t: Throwable) {
                Log.d("fail", t.toString())
            }

            override fun onResponse(call: Call<Random>, response: Response<Random>) {
                resultRandom.value=response.body()
            }


        })

    }
}