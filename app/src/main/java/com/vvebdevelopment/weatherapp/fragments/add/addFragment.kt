package com.vvebdevelopment.weatherapp.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vvebdevelopment.weatherapp.R
import com.vvebdevelopment.weatherapp.data.City
import com.vvebdevelopment.weatherapp.data.CityViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class addFragment : Fragment() {

    private lateinit var mCityViewModel: CityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        mCityViewModel = ViewModelProvider(this).get(CityViewModel::class.java)

        view.add_btn.setOnClickListener{
            insertDatatoDatabase()
        }

        return view
    }

    private fun insertDatatoDatabase(){
        val region = addRegion_et.text.toString()
        val cityName = addCity_et.text.toString()

        if(inputCheck(region, cityName)){
            val city = City(0, region, cityName)

            mCityViewModel.addCity(city)
            Toast.makeText(requireContext(), "Sikeres hozzáadás!", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(), "Hiányos beviteli adatok!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(region: String, cityName: String) : Boolean{
        return !(TextUtils.isEmpty(region) && TextUtils.isEmpty(cityName))
    }
}