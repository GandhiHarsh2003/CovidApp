package edu.quinnipiac.ser210.covidapp
/**
 *  Data class of Cases to retrieve specific cases parameters
 * @author Kevin Rodriguez and Harsh Gandhi
 * @date 4/4/2023
 */
data class Cases(
    val `1M_pop`: String,
    val active: Int,
    val critical: Int,
    val new: String,
    val recovered: Int,
    val total: Int
)