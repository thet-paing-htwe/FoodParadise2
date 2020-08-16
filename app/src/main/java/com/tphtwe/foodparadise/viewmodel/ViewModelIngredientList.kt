package com.tphtwe.foodparadise.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tphtwe.foodparadise.api.ApiClient
import com.tphtwe.foodparadise.model.ingredient.Ingredient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelIngredientList  : ViewModel(){
    private val listIngredient: MutableLiveData<Ingredient> = MutableLiveData()

    fun getListIngredient(): LiveData<Ingredient> = listIngredient

    fun loadListIngredient(ingredient:String){
        var apiClient= ApiClient()
        var call=apiClient.getIngredientListItems(ingredient)
        call.enqueue(object : Callback<Ingredient> {
            override fun onFailure(call: Call<Ingredient>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<Ingredient>, response: Response<Ingredient>) {
                listIngredient.value=response.body()!!
            }

        })


    }

}