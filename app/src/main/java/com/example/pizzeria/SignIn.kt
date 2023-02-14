package com.example.pizzeria

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mydailydiet.model.ServiceBuilder
import com.example.pizzeria.databinding.ActivitySignInBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

var currentUser = User(id = null, name = "", email = "", password = "")


class SignIn : AppCompatActivity() {
    var TAG = "SignIn"
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signInBtn.setOnClickListener {
                signIn()
        }

    }



    private fun signIn() {
        var email = binding.emailEditText.text.toString()
        var password = binding.passwordEditText.text.toString()


        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Completa los campos", Toast.LENGTH_SHORT).show()

        }else{


        ServiceBuilder.initService()
        val call = ServiceBuilder.service.getUsers()

        call.enqueue(object : Callback<Array<User>> {
            override fun onResponse(call: Call<Array<User>>, response: Response<Array<User>>) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    val mArray: Array<User> = body

                    val user = mArray.filter { email == it.email }

                    if (user.size > 0) {
                        if (user[0].password.equals(password)) {
                            Log.d(TAG, "Inicio de sesión con éxito")
                            currentUser = user[0]
                            val intent: Intent = Intent(this@SignIn, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this@SignIn,
                                "Error, compruebe sus datos",
                                Toast.LENGTH_SHORT
                            ).show()
                            binding.passwordEditText.error = "Contraseña incorrecta"
                        }

                    } else {
                        Toast.makeText(
                            this@SignIn,
                            "Error, compruebe sus datos",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.emailEditText.error = "Correo electrónico incorrecto"
                        Log.e(
                            "TAG",
                            response.errorBody()?.string()?.let { Log.e("TAG", it) }.toString()
                        )
                    }
                }
            }
            override fun onFailure(call: Call<Array<User>>, t: Throwable) {
                Log.e(TAG, "Error obteniendo usuarios")
            }
        })
    }
    }

}