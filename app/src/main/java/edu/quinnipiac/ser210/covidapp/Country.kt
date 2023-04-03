package edu.quinnipiac.ser210.covidapp
/**
 *  Data class of Country to retrieve specific country parameters
 * @author Kevin Rodriguez and Harsh Gandhi
 * @date 4/4/2023
 */
data class Country(
    val errors: List<Any>,
    val `get`: String,
    val parameters: List<Any>,
    val response: List<Response>,
    val results: Int
)