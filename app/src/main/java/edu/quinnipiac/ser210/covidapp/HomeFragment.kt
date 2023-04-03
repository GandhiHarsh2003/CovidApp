package edu.quinnipiac.ser210.covidapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.quinnipiac.ser210.covidapp.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Home Fragment will simply add the countries through a recycle view
 * Author: Kevin Rodriguez and Harsh Gandhi
 */
class HomeFragment : Fragment() {
    // add support for binding
    private lateinit var binding: FragmentHomeBinding
    // country adapter will be used
    private lateinit var countryAdapter: CountryAdapter
    private lateinit var countryView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // initialize country view
        countryView = binding.countryRecycleView
        // instantiate country adapter
        countryAdapter = CountryAdapter(requireContext(), Navigation.findNavController(view))
        // set the layout manager to linear layout
        countryView.layoutManager = LinearLayoutManager(requireContext())
        countryView.adapter = countryAdapter

        // initialize instance of our API interface
        val apiInterface = APIInterface.create().getCountries()

        if (apiInterface != null) {
            apiInterface.enqueue(object : Callback<CountriesResponse>{
                // if callback was successful, set the country adapter's list to list of countries response
                override fun onResponse(call: Call<CountriesResponse>, response: Response<CountriesResponse>) {
                    if (response.isSuccessful) {
                        val countriesResponse: CountriesResponse? = response.body()
                        if (countriesResponse != null && countriesResponse.response.isNotEmpty()) {
                            countryAdapter.countryList(countriesResponse.response)
                        }
                    }
                }


                // log message if call back failed
                override fun onFailure(call: Call<CountriesResponse>, t: Throwable) {
                    t.message?.let { Log.d("onFailure", it) }
                    Log.e("HomeFragment", "API CALL FAILED")
                }
            })
        }
    }


}