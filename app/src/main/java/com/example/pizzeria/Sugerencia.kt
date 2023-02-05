package com.example.pizzeria

import java.util.*

data class Sugerencia(
    val name : String,
    val ingredients : MutableList<String>,
    val user: User,
    val fecha: Date
)
