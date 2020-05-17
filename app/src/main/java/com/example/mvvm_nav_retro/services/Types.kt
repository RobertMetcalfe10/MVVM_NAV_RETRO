package com.example.mvvm_nav_retro.services

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
    val created_at: String,
    val updated_at: String
)

data class Restaurant(
    val id: Int,
    val name: String,
    val created_at: String,
    val updated_at: String
) {
    override fun toString(): String {
        return "$id, $name, $created_at, $updated_at"
    }
}