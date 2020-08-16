package com.tphtwe.foodparadise.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tphtwe.foodparadise.R
import com.tphtwe.foodparadise.model.AtoZmodel.Meal
import kotlinx.android.synthetic.main.item_a_to_z.view.*

class FirstLetterAdaptor(var firstLetterList:List<Meal> = ArrayList<Meal>()):RecyclerView.Adapter<FirstLetterAdaptor.FirstLetterViewHolder>(){
    var lclickListener2:ClickListener2?=null
    fun setOnclickListener(clickListener2:ClickListener2){
        this.lclickListener2=clickListener2
    }
    inner class FirstLetterViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView),View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }
        lateinit var meal2:Meal
        fun bindFirstLetter(meal: Meal){
            this.meal2=meal
            itemView.firstLetterTxt.text=meal.strMeal
            Picasso.get().load(meal.strMealThumb).placeholder(R.drawable.hhh).into(itemView.firstLetterImg)

        }

        override fun onClick(view: View?) {
            lclickListener2?.click(meal2)
        }

    }
    fun updateMealLetter(firstLetterList2:List<Meal>){
        this.firstLetterList=firstLetterList2
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstLetterViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.item_a_to_z,parent,false)
        return FirstLetterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return firstLetterList.size
    }

    override fun onBindViewHolder(holder: FirstLetterViewHolder, position: Int) {
        holder.bindFirstLetter(firstLetterList[position])
    }
    interface ClickListener2{
        fun click(meal: Meal)
    }
}