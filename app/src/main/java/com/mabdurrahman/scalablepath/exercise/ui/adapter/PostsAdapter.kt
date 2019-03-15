package com.mabdurrahman.scalablepath.exercise.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.mabdurrahman.scalablepath.exercise.R
import com.mabdurrahman.scalablepath.exercise.ui.model.PostItem

/**
 * Created by Mahmoud Abdurrahman (ma.abdurrahman@gmail.com) on 2019-03-15.
 */
class PostsAdapter: BaseAdapter() {

    var postItems: List<PostItem> = mutableListOf()
        set(items) {
            field = items
            notifyDataSetChanged()
        }

    override fun getView(position: Int, convertView: View?, parentView: ViewGroup): View? {

        var postView = convertView
        val viewHolder: PostItemViewHolder
        val postItem = postItems[position]

        if (convertView == null) {
            viewHolder = PostItemViewHolder()
            postView = LayoutInflater.from(parentView.context).inflate(R.layout.list_item, null)

            viewHolder.title = postView.findViewById(R.id.title) as TextView
            viewHolder.author = postView.findViewById(R.id.author) as TextView
            viewHolder.body = postView.findViewById(R.id.body) as TextView

            postView.tag = viewHolder
        } else {
            viewHolder = postView?.tag as PostItemViewHolder
        }

        viewHolder.title?.text = postItem.title
        viewHolder.author?.text = postItem.authorUsername
        viewHolder.body?.text = postItem.body

        return postView
    }

    override fun getItem(position: Int): Any {
        return postItems[position]
    }

    override fun getItemId(position: Int): Long {
        return postItems[position].id
    }

    override fun getCount(): Int {
        return postItems.size
    }

    internal class PostItemViewHolder {
        var title: TextView? = null
        var author: TextView? = null
        var body: TextView? = null
    }
}