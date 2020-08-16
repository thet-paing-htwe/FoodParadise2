package com.tphtwe.foodparadise.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tphtwe.foodparadise.api.ApiClient
import com.tphtwe.foodparadise.model.mealingredient.MealIngredient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelMealByIngredient : ViewModel(){
    private val ingredientMeal: MutableLiveData<MealIngredient> = MutableLiveData()

    fun getIngredientMeal(): LiveData<MealIngredient> = ingredientMeal

    fun loadIngredientMeal(itemIngredient:String){
        var apiClient= ApiClient()
        var call=apiClient.getMealByIngredients(itemIngredient)
        call.enqueue(object : Callback<MealIngredient> {
            override fun onFailure(call: Call<MealIngredient>, t: Throwable) {

            }

            override fun onResponse(call: Call<MealIngredient>, response: Response<MealIngredient>) {
                ingredientMeal.value=response.body()!!
            }

        })
    }

}