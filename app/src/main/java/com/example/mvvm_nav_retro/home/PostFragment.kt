package com.example.mvvm_nav_retro.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_nav_retro.R
import com.example.mvvm_nav_retro.databinding.FragmentGetBinding
import com.example.mvvm_nav_retro.databinding.FragmentPostBinding
import com.example.mvvm_nav_retro.utils.hideKeyboard
import kotlinx.android.synthetic.main.fragment_get.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostFragment : Fragment() {
    private val viewModel: PostViewModel by lazy {
        ViewModelProvider(this).get(PostViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPostBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.fragment = this
        return binding.root
    }

    fun addRestaurant() {
        lifecycleScope.launch(Dispatchers.Main) {
            if (!viewModel.addRestaurant()) Toast.makeText(
                context, "Adding Restaurant Failed",
                Toast.LENGTH_SHORT
            ).show()
            hideKeyboard()
        }
    }
}