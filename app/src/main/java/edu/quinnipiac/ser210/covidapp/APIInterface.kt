package edu.quinnipiac.ser210.covidapp

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIInterface {
    @GET("statistics")
    @Headers("X-RapidAPI-Key:83825d4d33msh8823d707b46e742p15aa23jsn05fcafc44de7", "X-RapidAPI-Host:covid-193.p.rapidapi.com")
    fun getCountries(): Call<CountriesResponse>
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