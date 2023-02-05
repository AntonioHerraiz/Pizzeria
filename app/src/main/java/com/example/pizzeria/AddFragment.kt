package com.example.pizzeria

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.forEach
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzeria.databinding.FragmentAddBinding
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
<<<<<<< HEAD

var sugerenciaList = mutableListOf<Sugerencia>()
=======
>>>>>>> github/main
class AddFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentAddBinding
    private lateinit var ingredients: Array<String>

<<<<<<< HEAD

=======
>>>>>>> github/main
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
        val recyclerView: RecyclerView = binding.ingredientsRecycler
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        ingredients = arrayOf("Champiñones", "Chocolate", "Tomate", "Queso Brie", "Jamón", "Atún")
        recyclerView.adapter = IngredientAdapter(ingredients)
<<<<<<< HEAD
        val sugerenciaRecyclerView: RecyclerView = binding.sugerenciasRecycler
        sugerenciaRecyclerView.layoutManager = LinearLayoutManager(this.context)
        sugerenciaRecyclerView.adapter = SugerenciaAdapter(sugerenciaList)
=======
>>>>>>> github/main

        binding.addSugerenciaBtn.setOnClickListener{
            val name: String = binding.namePizza.text.toString()
            val adapter = binding.ingredientsRecycler.adapter as IngredientAdapter

            val ingredientsList = adapter.getCheckBoxes()
            if (validate(name, ingredientsList)){
<<<<<<< HEAD
                val sugerencia = Sugerencia(name, ingredientsList, currentUser, Date())
                Log.i("Creando sugerencia", " $sugerencia")
                val emptyList = mutableListOf<String>()

                sugerenciaList.add(sugerencia)
                Toast.makeText(requireContext(), "Sugerencia añadida", Toast.LENGTH_SHORT).show()
                (sugerenciaRecyclerView.adapter as SugerenciaAdapter).notifyDataSetChanged()
=======
                val sugerencia = Sugerencia(name, ingredientsList)
                // Se guardaría en la API
                Log.i("Creando sugerencia", " " + sugerencia)
                Toast.makeText(requireContext(), "Sugerencia añadida", Toast.LENGTH_SHORT).show()
>>>>>>> github/main
            }
        }

    }
    fun validate(name : String, ingredientsList : MutableList<String>) : Boolean {
        var result = true

        if(name.isEmpty() || ingredientsList.size == 0){
            Toast.makeText(requireContext(), "Completa los campos", Toast.LENGTH_SHORT).show()
            result = false
        }
        if(name.length <= 5){
            Toast.makeText(requireContext(), "Tamaño del  nombre tiene que tener un minimo de 5 caracteres", Toast.LENGTH_SHORT).show()
            result = false
            binding.namePizza.error = "Tamaño insuficiente"
        }
        if(ingredientsList.size <=2){
            Toast.makeText(requireContext(), "Seleccion de ingredientes insuficientes", Toast.LENGTH_SHORT).show()
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