package edu.quinnipiac.ser210.covidapp

data class Response(
    val cases: Cases,
    val continent: String,
    val country: String,
    val day: String,
    val deaths: Deaths,
    val population: Int,
    val tests: Tests,
    val time: String
)