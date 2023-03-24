package edu.quinnipiac.ser210.covidapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView

private var countries = arrayListOf<Response>()
class CountryAdapter (private val context: Context, private val navController: NavController): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>()  {


  inner class CountryViewHolder (view: View, private val context: Context): RecyclerView.ViewHolder(view) {
      val countryTextView: TextView = view.findViewById(R.id.country_textView)
      val continetTextView: TextView = view.findViewById(R.id.continent_textView)

      // this will take user to to the country fragment
      init {
        view.setOnClickListener{
          navController.navigate(HomeFragmentDirections.actionHomeFragmentToCountryFragment())
        }
      }

      fun bindData(position: Int){
          val country: Response = countries.get(position)
          //val countryInfo
          //val countryInfo = country.response.get(position)
          countryTextView.text = country.country
          continetTextView.text = country.continent
      }
    }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item,parent,false)
      return CountryViewHolder(view, context)
  }

    fun countryList(list: ArrayList<Response>){
        countries = list
        notifyDataSetChanged()
    }

  override fun getItemCount(): Int {
    return countries.size
  }

  override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindData(position)
  }
}