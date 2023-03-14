package edu.quinnipiac.ser210.covidapp

data class Cases(
    val `1M_pop`: String,
    val active: Int,
    val critical: Int,
    val new: String,
    val recovered: Int,
    val total: Int
)