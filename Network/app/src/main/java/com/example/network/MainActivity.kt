package com.example.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import java.util.*

class MainActivity : AppCompatActivity(), Observer {

    override fun update(p0: Observable?, URL: Any?) {
        val avatarURL: String = URL as String

        val imageView: ImageView = findViewById<View>(R.id.avatarView) as ImageView
        Glide.with(this).load(avatarURL).into(imageView);
    }


    override fun onResume() {
        super.onResume()
        RetrofitObservable.getInstance()?.addObserver(this);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit: Retrofit =  Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val userName = "AlexVoyt"

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

        // val repos: List<Repository>? = github.listRepos("AlexVoyt").execute().body()


    }


}