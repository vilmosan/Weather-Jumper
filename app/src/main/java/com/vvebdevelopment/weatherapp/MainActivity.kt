package com.vvebdevelopment.weatherapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    var cityInputtedByUser = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.nextPageButton)
        button.setOnClickListener {
            val intent = Intent(this@MainActivity,SecondActivity::class.java)


            var editTextField = findViewById(R.id.cityInputField) as EditText

            cityInputtedByUser = editTextField.text.toString()

            Logger.getLogger(MainActivity::class.java.name).warning("City input: " + cityInputtedByUser)

            intent.putExtra("selectedCity", "budapest")
            startActivity(intent)
        }
    }
}