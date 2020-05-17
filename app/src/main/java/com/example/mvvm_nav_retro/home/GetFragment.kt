package com.example.mvvm_nav_retro.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_nav_retro.databinding.FragmentGetBinding
import kotlinx.android.synthetic.main.fragment_get.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetFragment : Fragment() {
    private val viewModel: GetViewModel by lazy {
        ViewModelProvider(this).get(GetViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGetBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.fragment = this
        binding.restaurantsRecyclerView.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    fun getRestaurants() {
        lifecycleScope.launch(Dispatchers.Main) {
            restaurants_recycler_view.adapter = RestaurantAdapter(viewModel.getRestaurants())
        }
    }
}
