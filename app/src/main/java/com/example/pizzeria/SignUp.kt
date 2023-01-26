package com.example.pizzeria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pizzeria.databinding.ActivitySignUpBinding
var userList : MutableList<User> = mutableListOf()
class SignUp : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupBtn.setOnClickListener{
            validate()
        }
    }



    private fun validate(){
        var name = binding.nameEditText.text.toString()
        var email = binding.emailEditText.text.toString()
        var password = binding.passwordEditText.text.toString()
        var password2 = binding.password2EditText.text.toString()
        var result = true

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()){
            Toast.makeText(this, "Completa los campos", Toast.LENGTH_SHORT).show()
            result = false
        }
        if (name.isEmpty() ){
            binding.nameEditText.error = "Completa tu nombre"
        }

        if (email.isEmpty() ){
            binding.emailEditText.error = "Completa tu email"
        }
        if (password.isEmpty()){
            binding.passwordEditText.error = "Completa la contraseña"
        }
        if (password2.isEmpty()){
            binding.password2EditText.error = "Completa tu nombre"
        }
        if (password.isNotEmpty() && password2.isNotEmpty() && password2 != password){
            result = false
        }
        if (result){
            val user = User(name, email, password)
            userList.add(user)
            val intent : Intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }
    }
}