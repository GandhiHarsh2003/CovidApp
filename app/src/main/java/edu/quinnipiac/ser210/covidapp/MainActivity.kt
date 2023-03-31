package edu.quinnipiac.ser210.covidapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ShareActionProvider
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.view.MenuItemCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import edu.quinnipiac.ser210.covidapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private  lateinit var  navController: NavController
    private lateinit var countryAdapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val builder = AppBarConfiguration.Builder(navController.graph)
        val appBarConfiguration = builder.build()
        toolbar.setupWithNavController(navController, appBarConfiguration)
        countryAdapter = CountryAdapter(this, navController)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.items_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.share -> {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                val stringBuilder = StringBuilder()

                // Get the currently displayed country in the CountryFragment
                val navHostFragment = supportFragmentManager.primaryNavigationFragment as NavHostFragment?
                val countryFragment = navHostFragment?.childFragmentManager?.primaryNavigationFragment as? CountryFragment
                if (countryFragment != null) {
                    val country = countryFragment?.let {countries.getOrNull(it.countryIndex) }

                    // Append data from the currently displayed country to the string builder
                    stringBuilder.append("Country: ${country?.country}\n")
                    stringBuilder.append("Continent: ${country?.continent ?: "N/A"}\n")
                    stringBuilder.append("Population: ${country?.population}\n")
                    stringBuilder.append("Total Cases: ${country?.cases?.total}\n")
                    stringBuilder.append("Critical Cases: ${country?.cases?.critical}\n")
                    stringBuilder.append("Recovered Cases: ${country?.cases?.recovered}\n")
                    stringBuilder.append("Total Deaths: ${country?.deaths?.total}\n\n")
                }

                shareIntent.putExtra(Intent.EXTRA_TEXT, stringBuilder.toString())
                startActivity(Intent.createChooser(shareIntent, "Share via"))

                true
            }
            else -> NavigationUI.onNavDestinationSelected(item!!, navController) || super.onOptionsItemSelected(item)
        }
    }
}