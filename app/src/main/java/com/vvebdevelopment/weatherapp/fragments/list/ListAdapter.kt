package com.vvebdevelopment.weatherapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vvebdevelopment.weatherapp.R
import com.vvebdevelopment.weatherapp.data.City

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var cityList = emptyList<City>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = cityList[position]

        holder.itemView.findViewById<TextView>(R.id.id_txt).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.region_txt).text = currentItem.region
        holder.itemView.findViewById<TextView>(R.id.city_txt).text = currentItem.city

    }

    fun setData(city: List<City>){
        this.cityList = city
        notifyDataSetChanged()
    }
}