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
/**
 *  Main Activity that creates instance of nav controller and logic for action bar
 * @author Kevin Rodriguez and Harsh Gandhi
 * @date 4/4/2023
 */
class MainActivity : AppCompatActivity() {

    private  lateinit var  navController: NavController
    private lateinit var countryAdapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // initialize toolbar
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        // initialize nav host
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        // configuration for tool bar
        val builder = AppBarConfiguration.Builder(navController.graph)
        val appBarConfiguration = builder.build()
        toolbar.setupWithNavController(navController, appBarConfiguration)
        // instantiate an instance of country adapter w/ parameter of context and nav controller
        countryAdapter = CountryAdapter(this, navController)
    }

    // inflates menu items
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.items_menu, menu)
        return true
    }
    // logic for when an item is selected in the tool bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // share logic
            //https://developer.android.com/training/sharing/send and https://stackoverflow.com/questions/50689206/how-i-can-retrieve-current-fragment-in-navhostfragment
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
            // navigate to other options configured in tool bar
            else -> NavigationUI.onNavDestinationSelected(item!!, navController) || super.onOptionsItemSelected(item)
        }
    }
}