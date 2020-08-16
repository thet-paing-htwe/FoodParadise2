package com.tphtwe.foodparadise.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tphtwe.foodparadise.R
import com.tphtwe.foodparadise.model.Area.Meal
import kotlinx.android.synthetic.main.item_area.view.*

class MealByCountryAdapter(var areaList:List<Meal> = ArrayList<Meal>()) :RecyclerView.Adapter<MealByCountryAdapter.CountryViewHolder>(){
    var mclicListener:ClickListener?=null
    fun setOnClickListener(clickListener:ClickListener){
        this.mclicListener=clickListener
    }
    inner class CountryViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView),View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }
        lateinit var meal2:Meal
        fun bind(meal: Meal){
            this.meal2=meal
            itemView.areaTitle.text=meal.strMeal
            Picasso.get().load(meal.strMealThumb).into(itemView.areaImage)
        }

        override fun onClick(view: View?) {
           mclicListener?.click(meal2)
        }
    }
    fun updateMealByCountry(areaList2: List<Meal>){
        this.areaList=areaList2
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.item_area,parent,false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
       return areaList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(areaList.get(position))
    }
    interface ClickListener{
        fun click(meal:Meal)
    }
}