package com.example.pizzeria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.pizzeria.databinding.ActivitySignUpBinding

// Lista de usuarios para el inicio de sesión
var userList : MutableList<User> = mutableListOf()

class SignUp : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding
    private var digitPattern = ".*[0-9].*"
    private var lowerPattern = ".*[a-z].*"
    private var upperPattern = ".*[A-Z].*"
    private var sizePattern = ".{4,}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupBtn.setOnClickListener{
            Log.i("INFO -> ", "Hola desde infor")
            Log.d("DEBUG -> ", "Hola desde debug")
            var validar = validate()
            if (validar)
                signUp()
        }
    }

    private fun signUp() {
        var name = binding.nameEditText.text.toString()
        var email = binding.emailEditText.text.toString()
        var password = binding.passwordEditText.text.toString()
        val user = User(name, email, password)
        userList.add(user)
        val intent : Intent = Intent(this, SignIn::class.java)
        startActivity(intent)
    }


    private fun validate() : Boolean{
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

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailEditText.error = "Completa tu email"
            result = false
        }
        if (password.isEmpty()){
            binding.passwordEditText.error = "Completa la contraseña"
        }
        else if (password2.isEmpty()){
            binding.password2EditText.error = "Completa la contraseña"
        }
        else if (!password.matches(digitPattern.toRegex())){
            binding.passwordEditText.error = "Es necesario incluir un dígito"
            result = false
        }
        else if (!password.matches(lowerPattern.toRegex())){
            binding.passwordEditText.error = "Es necesario incluir una letra minúscula"
            result = false
        }
        else if (!password.matches(upperPattern.toRegex())){
            binding.passwordEditText.error = "Es necesario incluir una letra mayúcula"
            result = false
        }
        else if (!password.matches(sizePattern.toRegex())){
            binding.passwordEditText.error = "Es necesario una longitud de 4 caracteres"
            result = false
        }
        if (password.isNotEmpty() && password2.isNotEmpty() && password2 != password){
            result = false
        }
        return result

    }
}