package com.mabdurrahman.scalablepath.exercise.network

import com.mabdurrahman.scalablepath.exercise.network.service.PostService
import com.mabdurrahman.scalablepath.exercise.util.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Mahmoud Abdurrahman (ma.abdurrahman@gmail.com) on 2019-03-15.
 */
object RestAdapter {

    fun getPostsService(): PostService {
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create())
            .baseUrl(Constants.ROOT_URL)
            .build()

        return retrofit.create(PostService::class.java)
    }
}