package com.mabdurrahman.scalablepath.exercise.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.mabdurrahman.scalablepath.exercise.R
import com.mabdurrahman.scalablepath.exercise.network.RestAdapter
import com.mabdurrahman.scalablepath.exercise.network.model.Post
import com.mabdurrahman.scalablepath.exercise.network.model.User
import com.mabdurrahman.scalablepath.exercise.ui.adapter.PostsAdapter
import com.mabdurrahman.scalablepath.exercise.ui.model.PostItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.functions.BiFunction

class MainActivity : AppCompatActivity() {

    var progress: View? = null
    var listView: ListView? = null
    val listAdapter = PostsAdapter()

    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listview)
        listView?.adapter = listAdapter

        progress = findViewById(R.id.progress)
    }

    override fun onStart() {
        super.onStart()

        fetchPosts()
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    private fun fetchPosts() {
        disposable = Observable.zip(
            RestAdapter.getPostsService().listPosts(),
            RestAdapter.getPostsService().listUsers(),
            BiFunction<List<Post>, List<User>, List<PostItem>>
            { posts, users ->
                val usersIndexed = users.map { it.id to it.name }.toMap()

                posts.map { post -> PostItem(
                    post.id,
                    post.title,
                    usersIndexed[post.userId] ?: getString(R.string.label_anonymous),
                    post.body)
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> showResult(result) },
                { error -> showError(error.message) }
            )
    }

    private fun showResult(list: List<PostItem>) {
        progress?.visibility = View.GONE

        listAdapter.postItems = list
    }

    private fun showError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}