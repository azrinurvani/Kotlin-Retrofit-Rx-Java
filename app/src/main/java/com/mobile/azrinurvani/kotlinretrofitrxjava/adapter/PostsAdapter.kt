package com.mobile.azrinurvani.kotlinretrofitrxjava.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.azrinurvani.kotlinretrofitrxjava.R
import com.mobile.azrinurvani.kotlinretrofitrxjava.model.PostModel
import kotlinx.android.synthetic.main.post_layout.view.*

class PostsAdapter (internal var context: Context, internal var postList: List<PostModel>)
    :RecyclerView.Adapter<PostViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.post_layout,parent,false)

        return PostViewHolder(view)
    }

    override fun getItemCount() = postList.size


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postList[position])
    }

}

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val title = itemView.title_text
    val content = itemView.content_text
    val author = itemView.author_text

    fun bind(items: PostModel){
        title.text = items.title
        content.text = items.body
        author.text = items.userId.toString()
    }
}
