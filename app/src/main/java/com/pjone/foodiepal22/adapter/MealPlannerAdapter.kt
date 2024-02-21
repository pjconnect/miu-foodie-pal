package com.pjone.foodiepal22.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pjone.foodiepal22.R
import com.pjone.foodiepal22.fragments.MealPlan

class MealPlannerAdapter(private val mealPlans: List<MealPlan>) :
    RecyclerView.Adapter<MealPlannerAdapter.MealPlanViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealPlanViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.meal_planner_item, parent, false)
        return MealPlanViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealPlanViewHolder, position: Int) {
        val mealPlan = mealPlans[position]
        holder.bind(mealPlan)
    }

    override fun getItemCount(): Int {
        return mealPlans.size
    }

    inner class MealPlanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dayTextView: TextView = itemView.findViewById(R.id.dayTextView)
        private val mealNameTextView: TextView = itemView.findViewById(R.id.mealNameTextView)

        fun bind(mealPlan: MealPlan) {
            dayTextView.text = mealPlan.day
            mealNameTextView.text = "Meal Name: " + mealPlan.mealName
        }
    }
}