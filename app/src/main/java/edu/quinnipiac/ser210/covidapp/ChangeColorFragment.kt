package edu.quinnipiac.ser210.covidapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import edu.quinnipiac.ser210.covidapp.databinding.FragmentChangeColorBinding
/**
 * Change Color fragment that configures the background color of the app
 * @author Kevin Rodriguez and Harsh Gandhi
 * @date 4/4/2023
 */
class ChangeColorFragment : Fragment(), View.OnClickListener {
    // use binding
    private lateinit var binding: FragmentChangeColorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentChangeColorBinding.inflate(layoutInflater)
        // get a reference of the three buttons and set onclick listeners for them
        binding.orangeButton.setOnClickListener(this)
        binding.whiteButton.setOnClickListener(this)
        binding.skyblueButton.setOnClickListener(this)
        return binding.root
    }



    // based on the color button that was clicked, change it to that color
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.orange_button -> {
                background(R.color.orange)
            }
            R.id.white_button -> {
                background(R.color.white)
            }
            R.id.skyblue_button -> {
                background(R.color.skyBlue)
            }
        }
    }

    // helper function that will change the background color of the app
    private fun background(Id: Int) {
        val colorChanging = ContextCompat.getColor(requireContext(), Id)
        requireActivity().findViewById<View>(android.R.id.content).setBackgroundColor(colorChanging)
    }
}