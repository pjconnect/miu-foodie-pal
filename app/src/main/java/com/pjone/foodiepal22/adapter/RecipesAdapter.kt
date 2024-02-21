package com.pjone.foodiepal22.adapter

import RecipeModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pjone.foodiepal22.R

class RecipesAdapter(private val recipeModels: List<RecipeModel>) :
    RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeModels[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int {
        return recipeModels.size
    }

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeName: TextView = itemView.findViewById(R.id.recipeNameEditText)
        private val cookingTimeTextView: TextView = itemView.findViewById(R.id.cookingTimeEditText)
        private val userRatingBar: RatingBar = itemView.findViewById(R.id.userRatingBar)

        fun bind(recipeModel: RecipeModel) {
            recipeName.text = "Name: ${recipeModel.recipeName}"
            cookingTimeTextView.text = "Cooking Time: ${recipeModel.cookingTime} minutes"
            userRatingBar.rating = recipeModel.userRating
        }
    }
}
