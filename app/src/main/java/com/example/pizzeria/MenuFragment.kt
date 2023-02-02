package com.example.pizzeria

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzeria.databinding.FragmentMenuBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentMenuBinding



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
    ): View? {
        binding = FragmentMenuBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = binding.menuRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        val cards = arrayOf(
            Pizza(
                "4 Quesos",
                "Queso azul / Mozzarella / Queso brie / Tomate",
                "https://s4d-images.telepizza.es/Products/Original/4_Quesos-2371.png",
                8.0
            ),
            Pizza(
                "Jamon queso",
                "Mozarrella / Jamon york / tomate",
                "https://newluxbrand.com/wp-content/uploads/2022/01/pizza-jamo%CC%81n-y-queso-Newlux.jpg",
                7.5
            ),
            Pizza(
                "Barbacoa ",
                " bacon / jamon / tomate",
                "https://static.guiainfantil.com/media/6084/c/pizza-barbacoa-un-clasico-para-ninos-md.jpg",
                8.6
            ),
            Pizza(
                "Champiñón",
                "champiñon / tomate",
                "https://www.2mandarinasenmicocina.com/wp-content/uploads/2018/01/pizza-de-champi%C3%B1ones-thermomix-receta.jpg",
                10.0
            )
        )
        recyclerView.adapter = MenuAdapter(cards)
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment menuFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}