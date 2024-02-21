package com.pjone.foodiepal22.fragments

import BlogModel
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pjone.foodiepal22.adapter.BlogAdapter
import com.pjone.foodiepal22.R

class BlogFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton

    private val blogModels: MutableList<BlogModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.blog_fragment, container, false)
        recyclerView = view.findViewById(R.id.blogRecyclerView)
        addButton = view.findViewById(R.id.addBlogButton)

        val adapter = BlogAdapter(blogModels)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        addButton.setOnClickListener {
            showAddBlogDialog(adapter)
        }

        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showAddBlogDialog(adapter: BlogAdapter) {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.add_blog_dialog, null)

        val blogTitleEditText: EditText = dialogView.findViewById(R.id.blogTitleEditText)
        val blogContentEditText: EditText = dialogView.findViewById(R.id.blogContentEditText)

        builder.setView(dialogView)
            .setTitle("Add Blog Post")
            .setPositiveButton("Add") { _, _ ->
                val blogTitle = blogTitleEditText.text.toString()
                val blogContent = blogContentEditText.text.toString()
                val newBlogModel = BlogModel(blogTitle, blogContent)
                blogModels.add(newBlogModel)
                adapter.notifyDataSetChanged()
            }
            .setNegativeButton("Cancel", null)

        builder.create().show()
    }
}