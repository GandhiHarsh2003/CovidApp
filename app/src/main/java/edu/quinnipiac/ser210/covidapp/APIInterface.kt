package edu.quinnipiac.ser210.covidapp

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
/**
 *  API interface that will take care of retrieving data from the API we've used for our project
 * @author Kevin Rodriguez and Harsh Gandhi
 * @date 4/4/2023
 */
interface APIInterface {
    // get data from the statistics endpoint
    // get countries can be called to retreive data from API (i.e. country, continents, cases, etc)
    @GET("statistics")
    @Headers("X-RapidAPI-Key:83825d4d33msh8823d707b46e742p15aa23jsn05fcafc44de7", "X-RapidAPI-Host:covid-193.p.rapidapi.com")
    fun getCountries(): Call<CountriesResponse>
    companion object{
        // pass in URL for api
        var Base_URL = "https://covid-193.p.rapidapi.com/"

        // create function that will build retrofit
        fun create(): APIInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Base_URL)
                .build()
            return retrofit.create(APIInterface::class.java)
        }
    }
}