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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        countryView = binding.countryRecycleView
        countryAdapter = CountryAdapter(requireContext(), Navigation.findNavController(view))
        countryView.layoutManager = LinearLayoutManager(requireContext())
        countryView.adapter = countryAdapter

        val apiInterface = APIInterface.create().getCountries()

        if (apiInterface != null) {
            apiInterface.enqueue(object : Callback<ArrayList<Country?>?>{
                override fun onResponse(
                    call: Call<ArrayList<Country?>?>,
                    response: Response<ArrayList<Country?>?>
                ) {
                    // this line is where the error occurs..
                    countryAdapter.countryList(response.body() !! as ArrayList<Country>)
                }

                override fun onFailure(call: Call<ArrayList<Country?>?>, t: Throwable) {
                    Log.e("HomeFragment", "API CALL FAILED")
                }
            })
        }
    }


}