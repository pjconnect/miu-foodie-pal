package com.pjone.foodiepal22.adapter

import BlogModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pjone.foodiepal22.R

class BlogAdapter(private val blogModels: MutableList<BlogModel>) :
    RecyclerView.Adapter<BlogAdapter.BlogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_blog_post, parent, false)
        return BlogViewHolder(view)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        val blogPost = blogModels[position]
        holder.bind(blogPost)
    }

    override fun getItemCount(): Int {
        return blogModels.size
    }

    inner class BlogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)

        fun bind(blogModel: BlogModel) {
            titleTextView.text = "Title:" + blogModel.title
            contentTextView.text = blogModel.content
        }
    }
}