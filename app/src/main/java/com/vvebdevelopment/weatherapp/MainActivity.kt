package com.vvebdevelopment.weatherapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.nextPageButton)
        button.setOnClickListener {
            val intent = Intent(this@MainActivity, ThirdActivity::class.java)

            val editTextField = findViewById(R.id.cityInputField) as EditText

            var cityInputtedByUser = editTextField.text.toString()
            cityInputtedByUser = cityInputtedByUser.trim().toLowerCase()
                .replace("é","e")
                .replace("á","a")
                .replace("ő","o")
                .replace("ú","u")
                .replace("ű","u")
                .replace("ö","o")
                .replace("ü","u")
                .replace("ó","o")
                .replace("í","i")
                .replace("õ","o")
                .replace("ō","o")
                .replace("á","a")
                .replace("ä","a")
                .replace("à","a")
                .replace("â","a")
                .replace("å","a")
                .replace("ã","a")

            //Logger.getLogger(SecondActivity::class.java.name).warning("City input: " + cityInputtedByUser)

            intent.putExtra("selectedCity", cityInputtedByUser)

            startActivity(intent);

        }
    }


}