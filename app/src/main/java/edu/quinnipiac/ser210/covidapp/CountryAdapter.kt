package edu.quinnipiac.ser210.covidapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView

var countries = arrayListOf<Response>()
class CountryAdapter (private val context: Context, private val navController: NavController): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>()  {
    inner class CountryViewHolder (view: View, private val context: Context): RecyclerView.ViewHolder(view) {
        val countryTextView: TextView = view.findViewById(R.id.country_textView)
        val continetTextView: TextView = view.findViewById(R.id.continent_textView)
        private var countryIndex = 0
        // this will take user to to the country fragment
        init {
            view.setOnClickListener{
                navController.navigate(HomeFragmentDirections.actionHomeFragmentToCountryFragment(countryIndex))
            }
        }

        fun bindData(position: Int){
            val country: Response = countries.get(position)
            countryIndex = position
            countryTextView.text = country.country
            // we noticed some countries don't have a continent listed
            if (country.continent == null){
                continetTextView.text = "N/A"
            } else {
                continetTextView.text = country.continent
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item,parent,false)
        return CountryViewHolder(view, context)
    }

    fun countryList(list: ArrayList<Response>?){
        if (list != null) {
            countries = list
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindData(position)
    }
}