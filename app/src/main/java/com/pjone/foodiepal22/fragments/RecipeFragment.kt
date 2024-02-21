package com.pjone.foodiepal22.fragments

import RecipeModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RatingBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pjone.foodiepal22.adapter.RecipesAdapter
import com.pjone.foodiepal22.R

class RecipeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton

    private val recipeModels: MutableList<RecipeModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.receipe_fragment, container, false)

        recyclerView = view.findViewById(R.id.recipesRecyclerView)
        addButton = view.findViewById(R.id.addRecipeButton)

        val adapter = RecipesAdapter(recipeModels)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        addButton.setOnClickListener {
            showAddRecipeDialog()
        }

        return view
    }

    private fun showAddRecipeDialog() {
        val dialogView = layoutInflater.inflate(R.layout.add_recipe_dialog, null)

        val recipeNameInput = dialogView.findViewById<EditText>(R.id.recipeNameEditText)
        val cookingTimeInput = dialogView.findViewById<EditText>(R.id.cookingTimeEditText)
        val userRatingBar = dialogView.findViewById<RatingBar>(R.id.userRatingBar)
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Add Recipe")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val recipeName = recipeNameInput.text.toString()
                val cookingTime = cookingTimeInput.text.toString()
                val userRating = userRatingBar.rating

                recipeModels.add(
                    RecipeModel(
                        recipeName,
                        cookingTime,
                        userRating,
                    )
                )

                recyclerView.adapter?.notifyDataSetChanged()
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }

}
