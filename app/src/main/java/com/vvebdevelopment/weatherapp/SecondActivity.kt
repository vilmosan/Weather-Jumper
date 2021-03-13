package com.vvebdevelopment.weatherapp

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Logger

class SecondActivity : AppCompatActivity() {

    var CITY: String = "pecs,hu"
    val API: String = "5432324ccb43c60f96fe1ae05a1c864b"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var city = intent.getStringExtra("selectedCity")
        Logger.getLogger(SecondActivity::class.java.name).warning("Selected city: " + city)

        //CITY = "$city,hu"

        weatherTask()

    }

    inner class weatherTask() : AsyncTask<String, Void, String>(){
        override fun onPreExecute() {
            super.onPreExecute()

            findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
            findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.GONE
            findViewById<TextView>(R.id.errortext).visibility = View.GONE
        }

        override fun doInBackground(vararg p0: String?): String? {
            var response: String?
            try {
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API")
                        .readText(Charsets.UTF_8)
            }
            catch (e: Exception){
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
                val updatedAt:Long = jsonObj.getLong("dt")
                val updatedAtText = "Utolsó frissítés: " + SimpleDateFormat("yyyy/MM/dd k:mm", Locale.ENGLISH).format(Date(updatedAt*1000))
                val temp = main.getString("temp")
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")
                val sunrise:Long = sys.getLong("sunrise")
                val sunset:Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                var weatherDescription = weather.getString("description")
                val address = jsonObj.getString("name") + ", " + sys.getString("country")
/*
                when (weatherDescription) {
                    "clear sky" -> weatherDescription = "tiszta égbolt"
                    "few clouds" -> weatherDescription = "kissé felhő"
                    "scattered clouds" -> weatherDescription = "szórványos felhők"
                    "broken clouds" -> weatherDescription = "bárányfelhők"
                    "shower rain" -> weatherDescription = "szakadó eső"
                    "rain" -> weatherDescription = "esős"
                    "thunderstorm" -> weatherDescription = "zivatar"
                    "snow" -> weatherDescription = "havas john"
                    "mist" -> weatherDescription = "ködös"
                    else -> {
                        weatherDescription = "meteorzápor"
                    }
                }

                findViewById<TextView>(R.id.app_title).text = address
                findViewById<TextView>(R.id.version_code).text = updatedAtText
                findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
                findViewById<TextView>(R.id.temperature).text = temp + "°C"
                findViewById<TextView>(R.id.sunrise).text = SimpleDateFormat("k:mm", Locale.ENGLISH).format(Date(sunrise*1000))
                findViewById<TextView>(R.id.sunset).text = SimpleDateFormat("k:mm", Locale.ENGLISH).format(Date(sunset*1000))
                findViewById<TextView>(R.id.wind).text = windSpeed + " km/óra"
                findViewById<TextView>(R.id.pressure).text = pressure + " Pa"
                findViewById<TextView>(R.id.humidity).text = humidity + "%"*/

                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE
            }
            catch (e: Exception){
                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                findViewById<TextView>(R.id.errortext).visibility = View.VISIBLE
            }
        }
    }
}