package com.example.pizzeria

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class IngredientAdapter (private var dataSet: Array<String>) :
    RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    private var listCheckboxes = mutableListOf<String>()
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            var name : CheckBox
            init {
                name = view.findViewById(R.id.nameCheckbox)
            }
        }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.ingredient, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataSet[position]
        holder.name.text = data
        holder.name.setOnCheckedChangeListener { checkboxBtn, isChecked ->
            if (isChecked.equals(true)){
                listCheckboxes.add(checkboxBtn.text.toString())
            }else{
                listCheckboxes.remove(checkboxBtn.text.toString())
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun getCheckBoxes() : MutableList<String>{
        return listCheckboxes
    }
    fun setCheckBoxes(checkboxesList : MutableList<String>) {
        listCheckboxes = checkboxesList
    }
}