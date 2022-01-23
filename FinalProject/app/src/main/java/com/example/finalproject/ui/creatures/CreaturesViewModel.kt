package com.example.finalproject.ui.creatures

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.data.Creature
import com.example.finalproject.data.Hero
import com.example.finalproject.network.HommAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CreaturesViewModel : ViewModel() {
/*
    private val _creatureList =
        listOf(
            Creature("Pikeman", 1, 4, 5, 1, 3, 10, 4, 14, 80, "Immune to jousting"),
            Creature("Halberdier", 1, 6, 5, 2, 3, 10, 5, 14, 115, "Immune to jousting"),
            Creature("Archer", 2, 6, 3, 2, 3, 10, 4, 9, 126, "Ranged (12 shots)"),
            Creature("Marksman", 2, 6, 3, 2, 3, 10, 6, 9, 184, "Ranged (24 shots), Double attack"),
            Creature("Griffin", 3, 8, 8, 3, 6, 25, 6, 7, 351, "Flying, Two retaliations"),
            Creature("Royal Griffin", 3, 9, 9, 3, 6, 25, 9, 7, 448, "Flying, Unlimited retaliations"),
            Creature("Swordsman", 4, 10, 12, 6, 9, 35, 5, 4, 445, ""),
            Creature("Crusader", 4, 12, 12, 7, 10, 35, 6, 4, 588, "Double attack"),
            Creature("Monk", 5, 12, 7, 10, 12, 30, 5, 3, 485, "Ranged (12 shots)"),
            Creature("Zealot", 5, 12, 10, 10, 12, 30, 7, 3, 750, "Ranged (24 shots), No melee penalty"),
            Creature("Cavalier", 6, 15, 15, 15, 25, 100, 7, 2, 1946, "Jousting"),
            Creature("Champion", 6, 16, 16, 20, 25, 100, 9, 2, 2100, "Jousting"),
            Creature("Angel", 7, 20, 20, 50, 50, 200, 12, 1, 5019, "Flying, Hates Devils and Arch Devils, Morale +1 to the whole army"),
            Creature("Archangel", 7, 30, 30, 50, 50, 250, 18, 1, 8776, "Flying, Hates Devils and Arch Devils, Resurrection, Morale +1 to the whole army")
        )


    // val creatureList: LiveData<List<Creature>> = _creatureList

    fun getCreatures() : List<Creature> {
        return _creatureList
    }
*/

    private var creatures: MutableLiveData<List<Creature>>? = null

    fun getCreatures(): LiveData<List<Creature>>? {
        if (creatures == null) {
            creatures = MutableLiveData()
            loadData()
        }
        return creatures
    }

    private fun loadData() {

        val retrofit: Retrofit =  Retrofit.Builder()
            .baseUrl("http://restapi.alexvoyt.com:13342/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val hommAPI: HommAPI = retrofit.create(HommAPI::class.java)

        hommAPI.listCreatures().enqueue(object: Callback<List<Creature>> {
            override fun onFailure(call: Call<List<Creature>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Creature>>, response: Response<List<Creature>>) {

                val _creatures = response.body()

                creatures!!.value = _creatures!!

            }
        })
    }
}
