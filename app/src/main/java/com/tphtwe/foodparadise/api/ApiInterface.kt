package com.tphtwe.foodparadise.api

import com.tphtwe.foodparadise.model.Area.Area
import com.tphtwe.foodparadise.model.AtoZmodel.FirstLetter
import com.tphtwe.foodparadise.model.Category
import com.tphtwe.foodparadise.model.Random
import com.tphtwe.foodparadise.model.country.Country
import com.tphtwe.foodparadise.model.ingredient.Ingredient
import com.tphtwe.foodparadise.model.mealingredient.MealIngredient
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("categories.php")
    fun getCategory():Call<Category>
    @GET("random.php")
    fun getRandom():Call<Random>

    //Ypp add first letter
    @GET("search.php")
    fun getFirstLetter(
        @Query("f")letter:String
    ):Call<FirstLetter>

    //add country spinner
    @GET("list.php")
    fun getCountryList(
        @Query("a")area:String
    ):Call<Country>

    //item by country
    @GET("filter.php")
    fun getMealByArea(
        @Query ("a")country:String
    ):Call<Area>

    //add ingredient spinner
    @GET("list.php")
    fun getIngredientList(
        @Query("i")ingredient:String
    ):Call<Ingredient>

    //item by ingredient
    @GET("filter.php")
    fun getMealByIngredient(
        @Query ("i")itemIngredient:String
    ):Call<MealIngredient>


}