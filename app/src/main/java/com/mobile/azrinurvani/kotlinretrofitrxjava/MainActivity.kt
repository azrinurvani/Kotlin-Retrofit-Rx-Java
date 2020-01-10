package com.mobile.azrinurvani.kotlinretrofitrxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.azrinurvani.kotlinretrofitrxjava.adapter.PostsAdapter
import com.mobile.azrinurvani.kotlinretrofitrxjava.model.PostModel
import com.mobile.azrinurvani.kotlinretrofitrxjava.network.IMyAPI
import com.mobile.azrinurvani.kotlinretrofitrxjava.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal lateinit var jsonApi : IMyAPI
    internal lateinit var compositeDisposable :  CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        initApi()
        initToRecycler()
        fetchData()
    }

    private fun initApi() {
        val retrofit = RetrofitClient.instance
        jsonApi = retrofit.create(IMyAPI::class.java)

    }

    private fun initToRecycler() {
        recyclerPosts.setHasFixedSize(true)
        recyclerPosts.layoutManager = LinearLayoutManager(this)

    }

    private fun fetchData(){
        compositeDisposable = CompositeDisposable()
        compositeDisposable.add(jsonApi.posts
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                    posts->displayData(posts)
            }
        )
    }

    private fun displayData(posts: List<PostModel>?) {
        val adapter = PostsAdapter(this, posts!!)
        recyclerPosts.adapter = adapter
    }
}
