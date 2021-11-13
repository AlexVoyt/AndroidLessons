package com.example.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

public interface GithubInfo {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String?): Call<List<Repository>>

    @GET("users/{user}")
    fun getUser(@Path("user") user: String?): Call<User>

    @GET("repos/{user}/{repo_name}")
    fun getRepo(@Path("user") user: String?, @Path("repo_name") repo_name: String?): Call<Repository>
}