package com.example.pizzeria

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MenuAdapter(private var dataSet: Array<Pizza>) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var title : TextView
        var ingredients: TextView
        var price : TextView
        var imageView : ImageView

        init {
            title = view.findViewById(R.id.nameTextView)
            ingredients = view.findViewById(R.id.ingredients)
            price = view.findViewById(R.id.priceTextView)
            imageView = view.findViewById(R.id.imageCard)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val data = dataSet[position]
        viewHolder.title.setText(data.name)
        viewHolder.ingredients.setText(data.ingredients)
        viewHolder.price.setText("${data.price.toString()} €")
        Picasso.get().load("${data.image}").into(viewHolder.imageView)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size


}