package com.example.pizzeria

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.pizzeria.databinding.FragmentMenuBinding
import com.example.pizzeria.databinding.FragmentUserProfileBinding
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentUserProfileBinding

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
        binding = FragmentUserProfileBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.userName.text = currentUser.name
        binding.emailUser.text = currentUser.email
        Picasso.get().load("https://definicion.de/wp-content/uploads/2019/07/perfil-de-usuario.png")
            .into(binding.fotoUser)

        binding.btnCerrar.setOnClickListener {
            currentUser.name = ""
            currentUser.email = ""
            currentUser.password = ""

            Toast.makeText(requireContext(), "Ha cerrado sesión", Toast.LENGTH_SHORT).show()

            val intent: Intent = Intent(requireContext(), SignIn::class.java)
            startActivity(intent)
        }

        binding.btnEliminar.setOnClickListener{
            val dialog = AlertDialog.Builder(requireContext())
                .setTitle(R.string.alerta)
                .setMessage(R.string.seguridad)
                .setPositiveButton(R.string.aceptar) { view, _ ->
                    currentUser.name = ""
                    currentUser.email = ""
                    currentUser.password = ""

                    //userList = mutableListOf()

                    Toast.makeText(requireContext(), "Ha borrado la cuenta", Toast.LENGTH_SHORT).show()

                    val intent: Intent = Intent(requireContext(), SignUp::class.java)
                    view.dismiss()
                    startActivity(intent)
                }
                .setNegativeButton(R.string.cancelar) { view, _ ->
                    view.dismiss()
                }
                .setCancelable(false)
                .create()

        dialog.show()
        }

        binding.btnPapelera.setOnClickListener{
            Picasso.get().load("https://definicion.de/wp-content/uploads/2019/07/perfil-de-usuario.png").into(binding.fotoUser)
        }
        binding.btnLoad.setOnClickListener{
            val openGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(openGallery, -1)
        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == -1) {
            val imageUri: Uri? = data?.data
            binding.fotoUser.setImageURI(imageUri)

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}