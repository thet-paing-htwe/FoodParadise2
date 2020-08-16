package com.tphtwe.foodparadise.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tphtwe.foodparadise.R
import com.tphtwe.foodparadise.model.CategoryX
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(var categoryList:List<CategoryX> = ArrayList<CategoryX>()) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){
    var categoryClickListener:ClickListener?=null
    fun setOnClickListener(clickListener: ClickListener){
        this.categoryClickListener=clickListener
    }
    inner class CategoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }
        lateinit var categoryX3: CategoryX
        fun bind(categoryX:CategoryX){
            this.categoryX3=categoryX
            itemView.categoryTitle.text=categoryX.strCategory
            Picasso.get().load(categoryX.strCategoryThumb).into(itemView.categoryImage)
        }

        override fun onClick(p0: View?) {
            categoryClickListener?.click(categoryX3)
        }
    }
    fun updateCategoryList(categoryList: List<CategoryX>){
        this.categoryList=categoryList
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }
    interface ClickListener{
        fun click(categoryX: CategoryX)
    }
}