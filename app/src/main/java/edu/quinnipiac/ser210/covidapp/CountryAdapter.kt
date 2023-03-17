package edu.quinnipiac.ser210.covidapp

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView

private val countries = arrayListOf<Country>()
class CountryAdapter (private val context: Context, private val navController: NavController): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>()  {


  inner class CountryViewHolder (view: View, private val context: Context): RecyclerView.ViewHolder(view) {

    }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
    TODO("Not yet implemented")
  }

  override fun getItemCount(): Int {
    return countries.size
  }

  override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
    TODO("Not yet implemented")
  }
}