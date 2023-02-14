package com.example.pizzeria

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class SugerenciaAdapter(private var dataSet: MutableList<Sugerencia>) :
    RecyclerView.Adapter<SugerenciaAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var title : TextView
        var ingredients : TextView
        var userName : TextView
        var date : TextView
        var deleteBtn : ImageView

        init {
            title = view.findViewById(R.id.titleSugerencia)
            ingredients = view.findViewById(R.id.ingredientsTextView)
            userName = view.findViewById(R.id.userName)
            date = view.findViewById(R.id.dateSugerencia)
            deleteBtn = view.findViewById(R.id.deleteImage)

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.sugerencia, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataSet[position]
        holder.title.text = data.name
        var textoIngredientes = "Ingredientes: \n"
        for (i in data.ingredients){
           textoIngredientes += " - ${i}\n"
        }
        holder.userName.text = "Usuario: ${data.user.name}"
        holder.ingredients.text = textoIngredientes
        holder.date.text = "Fecha: ${SimpleDateFormat("MMMM dd, YYYY").format(data.fecha).toUpperCase()}"

        holder.deleteBtn.setOnClickListener{
            val dialog = AlertDialog.Builder( holder.itemView.context)
                .setTitle(R.string.alerta)
                .setMessage(R.string.seguridad)
                .setPositiveButton(R.string.aceptar) { view, _ ->
                    dataSet.remove(data)
                    view.dismiss()
                }
                .setNegativeButton(R.string.cancelar) { view, _ ->
                    view.dismiss()
                }
                .setCancelable(false)
                .create()

            dialog.show()

        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size


}