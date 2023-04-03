package edu.quinnipiac.ser210.covidapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.quinnipiac.ser210.covidapp.databinding.FragmentCountryBinding

/**
 *  Country fragment will display information about the specific country the user selected
 * @author Kevin Rodriguez and Harsh Gandhi
 * @date 4/4/2023
 */
class CountryFragment : Fragment() {
    // use of binding
    private lateinit var binding: FragmentCountryBinding
    // countryIndex will get a reference of the index the country is stored in the list
    var countryIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set country index to the country index that was passed in from the home fragment
        countryIndex = CountryFragmentArgs.fromBundle(requireArguments()).countryIndex
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // initialize binding and return the root
        binding = FragmentCountryBinding.inflate(layoutInflater)
        return binding.root
    }

    // will set the proper text components to the data about that specific country
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.countryAPI.text = countries.get(countryIndex).country
        // we noticed some countries don't have a continent listed
        // checks to see if the data for continent is set to null
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