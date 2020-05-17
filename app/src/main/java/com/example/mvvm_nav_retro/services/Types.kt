package com.example.mvvm_nav_retro.services

import com.squareup.moshi.Json

data class LoginBody(
    val identifier: String,
    val password: String
)

data class LoginResult(
    val jwt: String,
    val user: User
)

data class User(
    val id: Int,
    val username: String,
    val email: String,
    val provider: String,
    val confirmed: Boolean,
    val blocked: Boolean,
    val role: Any,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String
)

data class Restaurant(
    val id: Int?,
    val name: String,
    @Json(name = "created_at") val createdAt: String?,
    @Json(name = "updated_at") val updatedAt: String?
) {
    override fun toString(): String {
        return "$id, $name, $createdAt, $updatedAt"
    }
}

data class RestaurantToAdd(val name: String)