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
 * Home Fragment
 * Author:
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var countryAdapter: CountryAdapter
    private lateinit var countryView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
//        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countryView = binding.countryRecycleView
        countryAdapter = CountryAdapter(requireContext(), Navigation.findNavController(view))
        countryView.layoutManager = LinearLayoutManager(requireContext())
        countryView.adapter = countryAdapter

        val apiInterface = APIInterface.create().getCountries()

        if (apiInterface != null) {
            apiInterface.enqueue(object : Callback<ArrayList<edu.quinnipiac.ser210.covidapp.Response?>?>{
                override fun onResponse(
                    call: Call<ArrayList<edu.quinnipiac.ser210.covidapp.Response?>?>,
                    response: Response<ArrayList<edu.quinnipiac.ser210.covidapp.Response?>?>
                ) {
                    println("hi")
                    Log.d("Messages", response.message())
                    Log.d("Headers", response.headers().toString())
                    Log.d("HomeFragment", "Response body: " + response.body().toString())
                    println("hi")
                    // this line is where the error occurs..
                    countryAdapter.countryList(response.body() !! as ArrayList<edu.quinnipiac.ser210.covidapp.Response>)

                }

                override fun onFailure(call: Call<ArrayList<edu.quinnipiac.ser210.covidapp.Response?>?>, t: Throwable) {
                    Log.e("HomeFragment", "API CALL FAILED")
                }
            })
        }
    }


}