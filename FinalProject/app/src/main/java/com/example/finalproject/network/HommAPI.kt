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
/*
val retrofit: Retrofit =  Retrofit.Builder()
            .baseUrl("http://restapi.alexvoyt.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val github: GithubInfo = retrofit.create(GithubInfo::class.java)

        github.getUser(userName).enqueue(object: Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {

            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user: User? = response.body()

                Log.d("AvatarURL", "${user?.avatarURL}")
                Log.d("Login", "${user?.login}")
                Log.d("Id", "${user?.id}")

                val loginText: TextView = findViewById<View>(R.id.loginView) as TextView
                loginText.text = user?.login

                RetrofitObservable.getInstance()?.notifyObserverWithResponse(user?.avatarURL)
            }
        })

        github.getRepo(userName, "AndroidLessons").enqueue(object: Callback<Repository> {
            override fun onFailure(call: Call<Repository>, t: Throwable) {

            }

            override fun onResponse(call: Call<Repository>, response: Response<Repository>) {
                val repo: Repository? = response.body()

                Log.d("Name", "${repo?.name}")
                Log.d("Id", "${repo?.id}")
                Log.d("Fork", "${repo?.fork}")
                Log.d("CreatedAt", "${repo?.createdAt}")
            }

        })
 */

