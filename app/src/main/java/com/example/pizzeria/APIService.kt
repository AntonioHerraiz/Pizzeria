package com.example.pizzeria
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @POST("users/add")
    fun addUser(
        @Body body : User
    ): Call<Void>

    @Headers("Content-Type: application/json")
    @GET("users")
    fun getUsers(
    ): Call<Array<User>>

    @Headers("Content-Type: application/json")
    @GET("users/{id}")
    fun getUserById(
        @Path("id") id: Int
    ): Call<User>

    @Headers("Content-Type: application/json")
    @GET("menus")
    fun getPizzas(
    ): Call<Array<Pizza>>

    @Headers("Content-Type: application/json")
    @GET("suggestions")
    fun getSuggestions(
    ): Call<Array<Sugerencia>>

    @Headers("Content-Type: application/json")
    @POST("suggestions/add")
    fun addSuggestion(
        @Body body : Sugerencia
    ): Call<Void>

    @Headers("Content-Type: application/json")
    @DELETE("suggestions/borrar/{id}")
    fun deleteSuggestion(@Path("id") id: Int): Call<Void>
}