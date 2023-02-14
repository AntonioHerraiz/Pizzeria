package com.example.pizzeria

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydailydiet.model.ServiceBuilder
import com.example.pizzeria.databinding.FragmentMenuBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private  var mArray : Array<Pizza> = arrayOf()

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var TAG = "MenuFragment"
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = binding.menuRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        if (mArray.isNullOrEmpty()){
            ServiceBuilder.initService()
            val call = ServiceBuilder.service.getPizzas()
            call.enqueue(object : Callback<Array<Pizza>> {
                override fun onResponse(call: Call<Array<Pizza>>, response: Response<Array<Pizza>>) {
                    val body = response.body()
                    if (response.isSuccessful && body != null) {
                        mArray = body
                        Log.d(TAG, "PIZZAS: " + mArray.toString())
                        recyclerView.adapter = MenuAdapter(mArray)
                    }
                }
                override fun onFailure(call: Call<Array<Pizza>>, t: Throwable) {
                    Log.e(TAG, "Error obteniendo menús")
                }
            })
        }else{
            recyclerView.adapter = MenuAdapter(mArray)
        }
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