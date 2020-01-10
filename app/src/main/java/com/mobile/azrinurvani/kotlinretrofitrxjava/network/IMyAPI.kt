package com.mobile.azrinurvani.kotlinretrofitrxjava.network

import com.mobile.azrinurvani.kotlinretrofitrxjava.model.PostModel
import io.reactivex.Observable
import retrofit2.http.GET

interface IMyAPI {

    @get:GET("posts")
    val posts:Observable<List<PostModel>>
}