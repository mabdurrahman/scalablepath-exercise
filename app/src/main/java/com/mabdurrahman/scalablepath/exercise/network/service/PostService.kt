package com.mabdurrahman.scalablepath.exercise.network.service

import com.mabdurrahman.scalablepath.exercise.network.model.Post
import com.mabdurrahman.scalablepath.exercise.network.model.User
import com.mabdurrahman.scalablepath.exercise.util.Constants
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Mahmoud Abdurrahman (ma.abdurrahman@gmail.com) on 2019-03-15.
 */
interface PostService {

    @GET(Constants.POSTS_ENDPOINT)
    fun listPosts(): Observable<List<Post>>

    @GET(Constants.USERS_ENDPOINT)
    fun listUsers(): Observable<List<User>>
}