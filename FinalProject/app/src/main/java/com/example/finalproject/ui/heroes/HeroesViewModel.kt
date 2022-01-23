package com.example.finalproject.ui.heroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.data.Hero
import com.example.finalproject.network.HommAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HeroesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is hero Fragment"
    }
    val text: LiveData<String> = _text

    private var heroes: MutableLiveData<List<Hero>>? = null

    fun getData(): LiveData<List<Hero>>? {
        if (heroes == null) {
            heroes = MutableLiveData()
            loadData()
        }
        return heroes
    }

    private fun loadData() {

        val retrofit: Retrofit =  Retrofit.Builder()
            .baseUrl("http://restapi.alexvoyt.com:13342/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val hommAPI: HommAPI = retrofit.create(HommAPI::class.java)

        hommAPI.listHeroes().enqueue(object: Callback<List<Hero>> {
            override fun onFailure(call: Call<List<Hero>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Hero>>, response: Response<List<Hero>>) {

                val _heroes = response.body()

                heroes!!.value = _heroes!!

            }
        })
    }
}