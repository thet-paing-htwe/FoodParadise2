package com.tphtwe.foodparadise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.tphtwe.foodparadise.AtoZList.AtoZList
import com.tphtwe.foodparadise.adapter.FirstLetterAdaptor
import com.tphtwe.foodparadise.api.ApiClient
import com.tphtwe.foodparadise.model.AtoZmodel.FirstLetter
import com.tphtwe.foodparadise.model.AtoZmodel.Meal
import kotlinx.android.synthetic.main.fragment_ato_z.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AtoZFragment : Fragment() , FirstLetterAdaptor.ClickListener2{
        lateinit var firstLetterAdaptor: FirstLetterAdaptor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ato_z, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstLetterAdaptor= FirstLetterAdaptor()
        firstLetterAdaptor.setOnclickListener(this)

        var listAto=AtoZList().arrayList


        val arrayAdapter= ArrayAdapter<String>(this.requireContext(), R.layout.support_simple_spinner_dropdown_item, listAto)
        spinner.adapter=arrayAdapter

        spinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
//                Toast.makeText(context, p0?.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show()
                var apiClient=ApiClient().getFirstLetter(p0?.getItemAtPosition(position).toString())
                apiClient.enqueue(object : Callback<FirstLetter>{
                    override fun onFailure(call: Call<FirstLetter>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                    override fun onResponse(call: Call<FirstLetter>, response: Response<FirstLetter>) {
                        atozRecycler.apply {
                            layoutManager=GridLayoutManager(context,2)
                            adapter=firstLetterAdaptor
                        }
                        firstLetterAdaptor.updateMealLetter(response.body()!!.meals)

                    }

                })
            }
        }

    }

    override fun click(meal: Meal) {
        Toast.makeText(context, meal.strMeal, Toast.LENGTH_SHORT).show()
    }


}