package com.tphtwe.foodparadise.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.tphtwe.foodparadise.R
import com.tphtwe.foodparadise.adapter.MealByCountryAdapter
import com.tphtwe.foodparadise.adapter.MealByIngredientAdapter
import com.tphtwe.foodparadise.model.mealingredient.Meal
import com.tphtwe.foodparadise.viewmodel.ViewModelIngredientList
import com.tphtwe.foodparadise.viewmodel.ViewModelMealByIngredient
import kotlinx.android.synthetic.main.fragment_ingredient_filter.*


class IngredientFilterFragment : Fragment() ,MealByIngredientAdapter.ClickListener{
    lateinit var viewModelIngredientList: ViewModelIngredientList
    lateinit var viewModelMealByIngredient: ViewModelMealByIngredient
    lateinit var listIngredient:ArrayList<String>
    lateinit var mealByIngredientAdapter: MealByIngredientAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredient_filter, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelIngredientList= ViewModelProvider(this).get(ViewModelIngredientList::class.java)
        viewModelMealByIngredient= ViewModelProvider(this).get(ViewModelMealByIngredient::class.java)
        mealByIngredientAdapter= MealByIngredientAdapter()
        mealByIngredientRecycler.apply {
            layoutManager= GridLayoutManager(context,2)
            adapter=mealByIngredientAdapter
        }
        mealByIngredientAdapter.setOnClickListener(this)
        listIngredient= ArrayList<String>()

        observeViewModel()
        ingredientSpinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                viewModelMealByIngredient.loadIngredientMeal(parent!!.getItemAtPosition(position).toString())
                observeAreaViewModel()
            }



        }
    }
    fun observeViewModel(){
        viewModelIngredientList.getListIngredient().observe(
            viewLifecycleOwner, Observer {
                for (item in 0 until it.meals.size){
                    listIngredient.add(it.meals[item].strIngredient.toString())
                }
                var arrayAdapter= ArrayAdapter<String>(this.requireContext(),R.layout.support_simple_spinner_dropdown_item,listIngredient)
                ingredientSpinner.adapter=arrayAdapter
            }
        )
    }
    fun observeAreaViewModel(){
        viewModelMealByIngredient.getIngredientMeal().observe(
            viewLifecycleOwner, Observer {
                mealByIngredientAdapter.updateMealByIngredient(it.meals)
            }
        )
    }

    override fun onResume() {
        super.onResume()
        viewModelIngredientList.loadListIngredient(listIngredient.toString())


    }

    override fun click(meal: Meal) {
        Toast.makeText(context, meal.strMeal.toString(), Toast.LENGTH_SHORT).show()
    }


}