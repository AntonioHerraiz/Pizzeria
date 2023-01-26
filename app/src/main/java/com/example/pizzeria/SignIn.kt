package com.example.pizzeria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pizzeria.databinding.ActivitySignInBinding
import com.example.pizzeria.databinding.ActivitySignUpBinding

class SignIn : AppCompatActivity() {
    private lateinit var binding : ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signinBtn.setOnClickListener{
            validate()
        }

    }
    private fun validate(){
        var email = binding.emailEditText.text.toString()
        var password = binding.passwordEditText.text.toString()
        var result = true

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Completa los campos", Toast.LENGTH_SHORT).show()
            result = false
        }

        for (user in userList){
            if (user.email.equals(email) && user.password.equals(password)){
                Toast.makeText(this, "Inicio de sesión satisfactorio", Toast.LENGTH_SHORT).show()
            }
            else{
                result = false
                Toast.makeText(this, "Error, compruebe sus datos", Toast.LENGTH_SHORT).show()
                if (!user.email.equals(email)  ){
                    binding.emailEditText.error = "Correo electrónico incorrecto"
                }
                if (!user.password.equals(password)){
                    binding.passwordEditText.error = "Contraseña incorrecta"
                }
            }
        }

        if (result){
            val intent : Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


}