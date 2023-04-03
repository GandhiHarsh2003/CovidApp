package edu.quinnipiac.ser210.covidapp
/**
 *  Data class of Response to retrieve specific response parameters
 * @author Kevin Rodriguez and Harsh Gandhi
 * @date 4/4/2023
 */
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