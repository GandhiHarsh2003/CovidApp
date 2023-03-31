package edu.quinnipiac.ser210.covidapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.quinnipiac.ser210.covidapp.databinding.FragmentCountryBinding


class CountryFragment : Fragment() {
    private lateinit var binding: FragmentCountryBinding
    var countryIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countryIndex = CountryFragmentArgs.fromBundle(requireArguments()).countryIndex
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.countryAPI.text = countries.get(countryIndex).country
        // we noticed some countries don't have a continent listed
        if (countries.get(countryIndex).continent == null){
            binding.continentAPI.text = "N/A"
        } else {
            binding.continentAPI.text = countries.get(countryIndex).continent
        }
        binding.populationAPI.text = countries.get(countryIndex).population.toString()
        binding.totalCasesAPI.text = countries.get(countryIndex).cases.total.toString()
        binding.criticalCasesAPI.text = countries.get(countryIndex).cases.critical.toString()
        binding.recoveryCasesAPI.text = countries.get(countryIndex).cases.recovered.toString()
        binding.deathsAPI.text = countries.get(countryIndex).deaths.total.toString()
    }
}