package edu.quinnipiac.ser210.covidapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface APIInterface {


    @GET("statistics/")
    fun getCountries(): Call<ArrayList<Country?>?>?
    companion object{
        var Base_URL = "https://covid-193.p.rapidapi.com/"
        fun create(): APIInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Base_URL)
                .build()
            return retrofit.create(APIInterface::class.java)
        }
    }
}