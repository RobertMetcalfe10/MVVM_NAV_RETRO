package com.example.mvvm_nav_retro.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_nav_retro.services.ApiService
import com.example.mvvm_nav_retro.services.Restaurant
import com.example.mvvm_nav_retro.services.RestaurantToAdd
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import java.lang.Exception

class PostViewModel : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    var restaurantToAdd: MutableLiveData<String> = MutableLiveData()

    fun restaurantTextChanged(text: Any) {
        restaurantToAdd.value = text.toString()
    }

    suspend fun addRestaurant(): Boolean {
        return withContext(coroutineScope.coroutineContext) {
            try {
                restaurantToAdd.value?.let {
                    ApiService.homeApiService.addRestaurant(
                        RestaurantToAdd(it)
                    ).await()
                }
                return@withContext true
            } catch (e: Exception) {
                println(e)
                return@withContext false
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
