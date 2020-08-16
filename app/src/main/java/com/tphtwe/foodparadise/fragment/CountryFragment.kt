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
import com.tphtwe.foodparadise.model.country.Country
import com.tphtwe.foodparadise.model.country.Meal
import com.tphtwe.foodparadise.viewmodel.ViewModelCountryList
import com.tphtwe.foodparadise.viewmodel.ViewModelMealByArea
import kotlinx.android.synthetic.main.fragment_country.*


class CountryFragment : Fragment(),MealByCountryAdapter.ClickListener{
    lateinit var viewModelCountryList: ViewModelCountryList
    lateinit var viewModelMealByArea: ViewModelMealByArea
    lateinit var listCountry:ArrayList<String>
    lateinit var mealByCountryAdapter: MealByCountryAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelCountryList=ViewModelProvider(this).get(ViewModelCountryList::class.java)
        viewModelMealByArea=ViewModelProvider(this).get(ViewModelMealByArea::class.java)
        mealByCountryAdapter=MealByCountryAdapter()
        mealByCountryRecycler.apply {
            layoutManager=GridLayoutManager(context,2)
            adapter=mealByCountryAdapter
        }
        mealByCountryAdapter.setOnClickListener(this)
        listCountry= ArrayList<String>()

        observeViewModel()
        countrySpinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                viewModelMealByArea.loadMeal(parent!!.getItemAtPosition(position).toString())
                observeAreaViewModel()
            }



        }
    }
    fun observeViewModel(){
        viewModelCountryList.getListCountry().observe(
            viewLifecycleOwner, Observer {
                for (item in 0 until it.meals.size){
                    listCountry.add(it.meals[item].strArea.toString())
                }
                var arrayAdapter=ArrayAdapter<String>(this.requireContext(),R.layout.support_simple_spinner_dropdown_item,listCountry)
                countrySpinner.adapter=arrayAdapter
            }
        )
    }
    fun observeAreaViewModel(){
        viewModelMealByArea.getareaMeal().observe(
            viewLifecycleOwner, Observer {
                mealByCountryAdapter.updateMealByCountry(it.meals)
            }
        )
    }

    override fun onResume() {
        super.onResume()
        viewModelCountryList.loadListCountry(listCountry.toString())


    }

    override fun click(meal: com.tphtwe.foodparadise.model.Area.Meal) {
        Toast.makeText(context, meal.strMeal.toString(), Toast.LENGTH_SHORT).show()
    }


}