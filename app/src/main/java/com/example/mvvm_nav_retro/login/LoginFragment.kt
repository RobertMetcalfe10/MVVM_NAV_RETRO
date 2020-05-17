package com.example.mvvm_nav_retro.login

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mvvm_nav_retro.R
import com.example.mvvm_nav_retro.databinding.FragmentLoginBinding
import com.example.mvvm_nav_retro.utils.hideKeyboard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.fragment = this
        return binding.root
    }

    fun login() {
        lifecycleScope.launch(Dispatchers.Main) {
            if (viewModel.login()
                    .isNotEmpty()
            ) {
                hideKeyboard()
                findNavController().navigate(R.id.action_loginFragment_to_getFragment)
            } else Toast.makeText(
                context, "Login Failed",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}
