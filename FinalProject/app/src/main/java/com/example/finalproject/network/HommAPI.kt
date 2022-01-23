package com.example.finalproject.network

import com.example.finalproject.data.Creature
import com.example.finalproject.data.Hero
import retrofit2.Call
import retrofit2.http.GET

interface HommAPI {
    @GET("homm/creatures")
    fun listCreatures(): Call<List<Creature>>

    @GET("homm/heroes")
    fun listHeroes(): Call<List<Hero>>

}

