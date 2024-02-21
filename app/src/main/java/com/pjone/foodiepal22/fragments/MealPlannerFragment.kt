package com.pjone.foodiepal22.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pjone.foodiepal22.adapter.MealPlannerAdapter
import com.pjone.foodiepal22.R
import java.text.SimpleDateFormat
import java.util.Calendar


class MealPlannerFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton

    private val mealPlans: MutableList<MealPlan> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.meal_planner, container, false)
        recyclerView = view.findViewById(R.id.mealPlannerRecyclerView)
        addButton = view.findViewById(R.id.addMealButton)

        val adapter = MealPlannerAdapter(mealPlans)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        addButton.setOnClickListener {
            showAddMealDialog()
        }

        return view
    }

    private fun showAddMealDialog() {
        val dialogView = layoutInflater.inflate(R.layout.add_meal_dialog, null)

        val timer: DatePicker = dialogView.findViewById(R.id.meal_timer)
        val mealNameEditText: EditText = dialogView.findViewById(R.id.mealNameEditText)


        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Add Meal to Planner")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val day: Int = timer.getDayOfMonth()
                val month: Int = timer.getMonth()
                val year: Int = timer.getYear()
                val calendar: Calendar = Calendar.getInstance()
                calendar.set(year, month, day)

                val sdf = SimpleDateFormat("dd-MM-yyyy")
                val formatedDate: String = sdf.format(calendar.getTime())

                val mealName = mealNameEditText.text.toString()

                mealPlans.add(MealPlan(formatedDate, mealName))

                recyclerView.adapter?.notifyDataSetChanged()
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }

}

data class MealPlan(
    val day: String,
    val mealName: String
)
