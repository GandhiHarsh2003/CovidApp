package edu.quinnipiac.ser210.covidapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import edu.quinnipiac.ser210.covidapp.databinding.FragmentChangeColorBinding

class ChangeColorFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentChangeColorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentChangeColorBinding.inflate(layoutInflater)
        binding.orangeButton.setOnClickListener(this)
        binding.whiteButton.setOnClickListener(this)
        binding.skyblueButton.setOnClickListener(this)
        return binding.root
    }



    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.orange_button -> {
                background(R.color.orange)
            }
            R.id.white_button -> {
                background(R.color.white)
            }
            R.id.skyblue_button -> {
                // it wouldn't let me give it a specific name, don't know why
                background(R.color.skyBlue)
            }
        }
    }

    private fun background(Id: Int) {
        val colorChanging = ContextCompat.getColor(requireContext(), Id)
        requireActivity().findViewById<View>(android.R.id.content).setBackgroundColor(colorChanging)
    }
}