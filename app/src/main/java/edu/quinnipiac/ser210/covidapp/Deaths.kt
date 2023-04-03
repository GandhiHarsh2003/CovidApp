package edu.quinnipiac.ser210.covidapp
/**
 *  Data class of Deaths to retrieve specific  parameters from api about death
 * @author Kevin Rodriguez and Harsh Gandhi
 * @date 4/4/2023
 */
data class Deaths(
    val `1M_pop`: String,
    val new: String,
    val total: Int
)