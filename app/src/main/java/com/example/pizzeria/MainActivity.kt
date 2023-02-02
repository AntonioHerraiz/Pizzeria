package com.example.pizzeria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.pizzeria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())


        binding.navBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.inicio ->
                    replaceFragment(HomeFragment())
                R.id.carta ->
                    replaceFragment(MenuFragment())
                R.id.perfil ->
                    replaceFragment(UserProfileFragment())
                R.id.add ->
                    replaceFragment(AddFragment())
                else -> {
                }
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.frameLayout, fragment)
        fragmentTransition.commit()
    }
}