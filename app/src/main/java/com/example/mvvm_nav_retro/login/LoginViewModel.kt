package com.example.mvvm_nav_retro.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_nav_retro.services.ApiService
import com.example.mvvm_nav_retro.services.LoginBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import java.lang.Exception

class LoginViewModel : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    var identifier: MutableLiveData<String> = MutableLiveData()

    var password: MutableLiveData<String> = MutableLiveData()

    fun identifierTextChanged(text: Any) {
        identifier.value = text.toString()
    }

    fun passwordTextChanged(text: Any) {
        password.value = text.toString()
    }

    suspend fun login(): String {
        return withContext(coroutineScope.coroutineContext) {
            try {
                ApiService.jwtToken = ApiService.loginApiService.login(
                    LoginBody(
                        identifier.value!!,
                        password.value!!
                    )
                ).await().jwt
            } catch (e: Exception) {
                println(e)
                return@withContext ""
            }
            return@withContext ApiService.jwtToken
        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}