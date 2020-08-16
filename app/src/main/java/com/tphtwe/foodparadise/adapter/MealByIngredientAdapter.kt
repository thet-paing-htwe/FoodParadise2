package com.tphtwe.foodparadise.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tphtwe.foodparadise.R
import com.tphtwe.foodparadise.model.mealingredient.Meal

import kotlinx.android.synthetic.main.item_ingredient.view.*

class MealByIngredientAdapter (var ingredientList:List<Meal> = ArrayList<Meal>()) : RecyclerView.Adapter<MealByIngredientAdapter.IngredientViewHolder>(){
    var iclicListener:ClickListener?=null
    fun setOnClickListener(clickListener:ClickListener){
        this.iclicListener=clickListener
    }
    inner class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }
        lateinit var meal2: Meal
        fun bind(meal: Meal){
            this.meal2=meal
            itemView.ingredientTitle.text=meal.strMeal
            Picasso.get().load(meal.strMealThumb).into(itemView.ingredientImage)
        }

        override fun onClick(view: View?) {
            iclicListener?.click(meal2)
        }
    }

    fun updateMealByIngredient(ingredientList2: List<Meal>){
        this.ingredientList=ingredientList2
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        var view= LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient,parent,false)
        return IngredientViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(ingredientList.get(position))
    }
    interface ClickListener{
        fun click(meal: Meal)
    }
}