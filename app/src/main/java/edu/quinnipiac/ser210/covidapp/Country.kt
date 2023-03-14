package edu.quinnipiac.ser210.covidapp

data class Country(
    val errors: List<Any>,
    val `get`: String,
    val parameters: List<Any>,
    val response: List<Response>,
    val results: Int
)