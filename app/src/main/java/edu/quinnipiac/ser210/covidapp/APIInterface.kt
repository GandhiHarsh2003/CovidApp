package edu.quinnipiac.ser210.covidapp

import android.telecom.Call
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIInterface {
    @GET("Statistics/")
    fun getCountries(): Call<ArrayList<CovidStats?>>
    companion object{
        var Base_URL = "https://rapidapi.com/api-sports/api/covid-193/"
        fun create(): APIInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Base_URL)
                .build()
            return retrofit.create(APIInterface::class.java)
        }
    }
}