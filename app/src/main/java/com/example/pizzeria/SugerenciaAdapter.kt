package com.example.pizzeria

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydailydiet.model.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface DeleteClickedListener {
    fun onDeleteClicked(id: Int, pos: Int)
}


class SugerenciaAdapter(
    private var dataSet: Array<Sugerencia>,
    private val deleteClickedListener: DeleteClickedListener
) :
    RecyclerView.Adapter<SugerenciaAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var title: TextView
        var ingredients: TextView
        var userName: TextView
        var deleteBtn: ImageView

        init {
            title = view.findViewById(R.id.titleSugerencia)
            ingredients = view.findViewById(R.id.ingredientsTextView)
            userName = view.findViewById(R.id.userName)
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

        ServiceBuilder.initService()
        val call = ServiceBuilder.service.getUserById(data.user_id)
        call.enqueue(object : Callback<Array<User>> {
            override fun onResponse(call: Call<Array<User>>, response: Response<Array<User>>) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    holder.userName.text = "Usuario: ${body[0].name}"
                }
            }

            override fun onFailure(call: Call<Array<User>>, t: Throwable) {
                holder.userName.text = "Usuario no encontrado"
            }
        })

        holder.ingredients.text = "Ingredientes: ${data.ingredients}"
        holder.deleteBtn.setOnClickListener {

            val dialog = AlertDialog.Builder(holder.itemView.context)
                .setTitle(R.string.alerta)
                .setMessage(R.string.seguridadSuge)
                .setPositiveButton(R.string.aceptar) { view, _ ->
                    dataSet[position].id?.let { it1 -> deleteClickedListener.onDeleteClicked(it1, position) }
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