package com.example.mvvm_nav_retro.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_nav_retro.databinding.RestaurantBinding
import com.example.mvvm_nav_retro.services.Restaurant
import kotlinx.android.synthetic.main.restaurant.view.*

class RestaurantAdapter(
    private val listRestaurant: List<Restaurant>?
) : RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RestaurantBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.idTextView.text = listRestaurant?.get(position)?.id.toString()
        holder.nameTextView.text = listRestaurant?.get(position)?.name
        holder.createdAtTextView.text = listRestaurant?.get(position)?.createdAt
        holder.updatedAtTextView.text = listRestaurant?.get(position)?.updatedAt
    }

    override fun getItemCount(): Int = listRestaurant!!.size

    class ViewHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView) {
        val idTextView: TextView = itemsView.IdTextView
        val nameTextView: TextView = itemsView.NameTextView
        val createdAtTextView: TextView = itemsView.CreatedAtTextView
        val updatedAtTextView: TextView = itemsView.UpdatedAtTextView
    }
}