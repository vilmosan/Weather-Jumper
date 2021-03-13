package com.vvebdevelopment.weatherapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

import androidx.appcompat.app.AppCompatActivity
import java.util.logging.Logger


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.nextPageButton)
        button.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)

            val editTextField = findViewById(R.id.cityInputField) as EditText

            val cityInputtedByUser = editTextField.text.toString()
            cityInputtedByUser.trim().toLowerCase()
                    .replace("é","e")
                    .replace("á","a")
                    .replace("ő","o")
                    .replace("ú","u")
                    .replace("ű","u")
                    .replace("ö","o")
                    .replace("ü","u")
                    .replace("ó","o")
                    .replace("í","i")

            Logger.getLogger(MainActivity::class.java.name).warning("City input: " + cityInputtedByUser)

            intent.putExtra("selectedCity", cityInputtedByUser)

            startActivity(intent);

        }

    }

}