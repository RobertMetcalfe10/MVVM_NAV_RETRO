package com.example.mvvm_nav_retro.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_nav_retro.services.ApiService
import com.example.mvvm_nav_retro.services.Restaurant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import java.lang.Exception

class GetViewModel : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    private var restaurants: MutableLiveData<List<Restaurant>> = MutableLiveData()

    suspend fun getRestaurants(): List<Restaurant>? {
        return withContext(coroutineScope.coroutineContext) {
            try {
                restaurants.let {
                    restaurants.value = ApiService.homeApiService.getRestaurants().await()
                }
                return@withContext restaurants.value
            } catch (e: Exception) {
                println(e)
                return@withContext emptyList<Restaurant>()
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
