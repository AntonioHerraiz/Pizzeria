package com.example.pizzeria

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydailydiet.model.ServiceBuilder
import com.example.pizzeria.databinding.FragmentAddBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private var mArray: Array<Sugerencia> = arrayOf<Sugerencia>()

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class AddFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentAddBinding
    private lateinit var ingredients: Array<String>
    var TAG = "SugerenciasFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View? {
        binding = FragmentAddBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ingredients = arrayOf(
            "Champiñones",
            "Chocolate",
            "Tomate",
            "Queso Brie",
            "Jamón York",
            "Atún",
            "Mozarella",
            "Rúcula",
            "Gambas",
            "Carne de vacuno",
            "Pimiento asado",
            "Pimiento verde"
        )
        val recyclerViewIngredients: RecyclerView = binding.ingredientsRecycler
        recyclerViewIngredients.layoutManager = GridLayoutManager(this.context, 2)
        recyclerViewIngredients.adapter = IngredientAdapter(ingredients)

        val recyclerViewSugerencias: RecyclerView = binding.sugerenciasRecycler
        recyclerViewSugerencias.layoutManager = LinearLayoutManager(this.context)

        ServiceBuilder.initService()
        val call = ServiceBuilder.service.getSuggestions()
        call.enqueue(object : Callback<Array<Sugerencia>> {
            override fun onResponse(
                call: Call<Array<Sugerencia>>,
                response: Response<Array<Sugerencia>>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    mArray = body
                    Log.d(TAG, "Sugerencias: " + mArray.toString())
                    recyclerViewSugerencias.adapter = SugerenciaAdapter(mArray, object : DeleteClickedListener {
                        override fun onDeleteClicked(id: Int, pos: Int) {
                            ServiceBuilder.initService()
                            val call = ServiceBuilder.service.deleteSuggestion(id)
                            call.enqueue(object : Callback<Void> {
                                override fun onResponse(
                                    call: Call<Void>,
                                    response: Response<Void>
                                ) {
                                    val result = mArray.toMutableList()
                                    result.removeAt(pos)
                                    mArray =  result.toTypedArray()
                                    recyclerViewSugerencias.adapter?.notifyItemRemoved(pos)
                                    Log.i(TAG, "Borrado completado")
                                }

                                override fun onFailure(call: Call<Void>, t: Throwable) {
                                    Log.e(TAG, "Error obteniendo Sugerencias")
                                }
                            })
                        }
                    })
                }
            }

            override fun onFailure(call: Call<Array<Sugerencia>>, t: Throwable) {
                Log.e(TAG, "Error obteniendo Sugerencias")
            }
        })


        binding.addSugerenciaBtn.setOnClickListener {
            val name: String = binding.nameSugerenciaEditText.text.toString()
            val adapter = binding.ingredientsRecycler.adapter as IngredientAdapter

            val ingredientsList = adapter.getCheckBoxes()
            if (validate(name, ingredientsList)) {

                var stringIngredients = ""
                for (i in ingredientsList) {
                    stringIngredients += i + " / "
                }

                stringIngredients = stringIngredients.substring(0, stringIngredients.length-3)

                ServiceBuilder.initService()

                var sugerencia = currentUser.id?.let { it1 ->
                    Sugerencia(
                        null, name,
                        it1, stringIngredients
                    )
                }

                if (sugerencia != null) {
                    val call = ServiceBuilder.service.addSuggestion(sugerencia)
                    call.enqueue(object : Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            Toast.makeText(
                                requireContext(),
                                "Sugerencia registrada con éxito",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            Toast.makeText(requireContext(), "Sugerencia añadida", Toast.LENGTH_SHORT).show()
                            recyclerViewSugerencias.adapter?.notifyDataSetChanged()

                            val call = ServiceBuilder.service.getSuggestions()
                            call.enqueue(object : Callback<Array<Sugerencia>> {
                                override fun onResponse(
                                    call: Call<Array<Sugerencia>>,
                                    response: Response<Array<Sugerencia>>
                                ) {
                                    val body = response.body()
                                    if (response.isSuccessful && body != null) {
                                        mArray = body
                                        Log.d(TAG, "Sugerencias: " + mArray.toString())
                                        recyclerViewSugerencias.adapter = SugerenciaAdapter(mArray, object : DeleteClickedListener {
                                            override fun onDeleteClicked(id: Int, pos: Int) {
                                                ServiceBuilder.initService()
                                                val call = ServiceBuilder.service.deleteSuggestion(id)
                                                call.enqueue(object : Callback<Void> {
                                                    override fun onResponse(
                                                        call: Call<Void>,
                                                        response: Response<Void>
                                                    ) {
                                                        val result = mArray.toMutableList()
                                                        result.removeAt(pos)
                                                        mArray =  result.toTypedArray()
                                                        recyclerViewSugerencias.adapter?.notifyItemRemoved(pos)
                                                        /*recyclerViewSugerencias.adapter?.notifyItemRemoved(pos)
                                                        recyclerViewSugerencias.adapter?.notifyItemRangeChanged(pos, mArray.size - pos)*/
                                                        Log.i(TAG, "Borrado completado")
                                                    }

                                                    override fun onFailure(call: Call<Void>, t: Throwable) {
                                                        Log.e(TAG, "Error obteniendo Sugerencias")
                                                    }
                                                })
                                            }
                                        })

                                    }
                                }

                                override fun onFailure(call: Call<Array<Sugerencia>>, t: Throwable) {
                                    Log.e(TAG, "Error obteniendo Sugerencias")
                                }
                            })
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            Toast.makeText(requireContext(), "Error creando sugerencia", Toast.LENGTH_SHORT)
                                .show()
                        }
                    })
                }
            }
        }

    }

    fun validate(name: String, ingredientsList: MutableList<String>): Boolean {
        var result = true

        if (name.isEmpty() || ingredientsList.size == 0) {
            Toast.makeText(requireContext(), "Completa los campos", Toast.LENGTH_SHORT).show()
            result = false
        }
        if (name.length <= 5) {
            Toast.makeText(
                requireContext(),
                "Tamaño del  nombre tiene que tener un minimo de 5 caracteres",
                Toast.LENGTH_SHORT
            ).show()
            result = false
            binding.nameSugerenciaEditText.error = "Tamaño insuficiente"
        }
        if (ingredientsList.size <= 2) {
            Toast.makeText(
                requireContext(),
                "Seleccion de ingredientes insuficientes",
                Toast.LENGTH_SHORT
            ).show()
            result = false
            binding.ingredientsTitle.error = "Seleccione más ingredientes"
        }

        return result

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}