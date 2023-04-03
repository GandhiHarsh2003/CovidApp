package edu.quinnipiac.ser210.covidapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
/**
 * Country Adapter that configures the binding logic for the recycle view
 * @author Kevin Rodriguez and Harsh Gandhi
 * @date 4/4/2023
 */

// store the information retrieved from api in arraylist
var countries = arrayListOf<Response>()
class CountryAdapter (private val context: Context, private val navController: NavController): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>()  {

    // country view holder contains logic for binding the data
    inner class CountryViewHolder (view: View, private val context: Context): RecyclerView.ViewHolder(view) {
        // initialize the text views that are in the country item xml
        // these will list data about countries
        val countryTextView: TextView = view.findViewById(R.id.country_textView)
        val continetTextView: TextView = view.findViewById(R.id.continent_textView)
        // country index to refer to a certain index within the countries array list
        private var countryIndex = 0
        // this will take user to to the country fragment when the user clicks on a country
        init {
            view.setOnClickListener{
                navController.navigate(HomeFragmentDirections.actionHomeFragmentToCountryFragment(countryIndex))
            }
        }

        // logic for binding the data to our app
        fun bindData(position: Int){
            // get a certain country from the arraylist that stores the countries
            val country: Response = countries.get(position)
            // sets countryIndex to the current position
            countryIndex = position
            countryTextView.text = country.country
            // we noticed some countries don't have a continent listed
            // checks to see if the data for continent is set to null
            if (country.continent == null){
                continetTextView.text = "N/A"
            } else {
                continetTextView.text = country.continent
            }
        }
    }

    // inflates the country item layout and instantiate  country view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item,parent,false)
        return CountryViewHolder(view, context)
    }

    // sets the countries list to a list (this will be the list of countries retrieved from api)
    fun countryList(list: ArrayList<Response>?){
        if (list != null) {
            countries = list
            // notifies recycle view once it has received data
            notifyDataSetChanged()
        }
    }

    // returns the size of the countries array list
    override fun getItemCount(): Int {
        return countries.size
    }

    // will simply bind data at a certain index
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindData(position)
    }
}