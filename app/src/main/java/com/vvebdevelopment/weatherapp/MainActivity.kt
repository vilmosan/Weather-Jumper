package com.vvebdevelopment.weatherapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val findWeatherButton = findViewById<Button>(R.id.nextPageButton)
        findWeatherButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ThirdActivity::class.java)

            val editTextField = findViewById<EditText>(R.id.cityInputField)

            var cityInputtedByCity = editTextField.text.toString()
            cityInputtedByCity = cityInputtedByCity.trim().toLowerCase()
                .replace("é", "e")
                .replace("á", "a")
                .replace("ő", "o")
                .replace("ú", "u")
                .replace("ű", "u")
                .replace("ö", "o")
                .replace("ü", "u")
                .replace("ó", "o")
                .replace("í", "i")
                .replace("õ", "o")
                .replace("ō", "o")
                .replace("á", "a")
                .replace("ä", "a")
                .replace("à", "a")
                .replace("â", "a")
                .replace("å", "a")
                .replace("ã", "a")

            //Logger.getLogger(SecondActivity::class.java.name).warning("City input: " + cityInputtedByCity)

            intent.putExtra("selectedCity", cityInputtedByCity)

            startActivity(intent)
        }

        val jumpToDatabase = findViewById<Button>(R.id.databasePageButton)
        jumpToDatabase.setOnClickListener {
            val databaseIntent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(databaseIntent)
        }
    }


}