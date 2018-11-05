package iimdemo.killiangalea.com.iimdemo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface API {
    @GET("users/aosp-mirror/repos")
    fun getRepo(): Call<List<Repo>>

    @GET("repos/aosp-mirror/{name}")
    fun getFullRepo(@Path("name") name: String ): Call<FullRepo>

    @GET("repos/aosp-mirror/{name}/commits")
    fun getCommits(@Path("name") name: String ): Call<List<MyCommit>>


}