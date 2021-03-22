package com.vvebdevelopment.weatherapp

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class ThirdActivity : AppCompatActivity() {

    var CITY: String = "pecs,hu"
    val API: String = "06c921750b9a82d8f5d1294e1586276f"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val exitButton = findViewById<Button>(R.id.exitButton)
        exitButton.setOnClickListener {
            finish();
            System.exit(0);
        }

        var city = intent.getStringExtra("selectedCity")
        //Logger.getLogger(SecondActivity::class.java.name).warning("Selected city: " + city)

        CITY = "$city,hu"

        weatherTask().execute()

    }

    inner class weatherTask : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            /* Showing the ProgressBar, Making the main design GONE */
            findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
            findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.GONE
            findViewById<TextView>(R.id.errorText).visibility = View.GONE
        }

        override fun doInBackground(vararg params: String?): String? {
            var response: String?
            try {
                response =
                    URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API").readText(
                        Charsets.UTF_8
                    )
            } catch (e: Exception) {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                //Logger.getLogger(SecondActivity::class.java.name).warning("JSON: " + jsonObj)

                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)

                val updatedAt: Long = jsonObj.getLong("dt")
                val updatedAtText = "Updated at: " + SimpleDateFormat(
                    "yyyy.MM.dd k:mm",
                    Locale.ENGLISH
                ).format(Date(updatedAt * 1000))
                val temp = main.getString("temp")
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")

                val sunrise: Long = sys.getLong("sunrise")
                val sunset: Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                var weatherDescription = weather.getString("main")
                //Logger.getLogger(SecondActivity::class.java.name).warning("Weather: " + weatherDescription)

                val address = jsonObj.getString("name") + ", " + sys.getString("country")

                weatherDescription = when (weatherDescription) {
                    "Clear" -> "Tiszta égbolt"
                    "Clouds" -> "Felhős"
                    "Rain" -> "Esős"
                    "Drizzle" -> "Csepereg"
                    "Thunderstorm" -> "Zivatar"
                    "Snow" -> "Havas John"
                    "Mist" -> "Ködös"
                    "Fog" -> "Ködös"
                    else -> {
                        "Meteorzápor"
                    }
                }

                /* Populating extracted data into our views */
                findViewById<TextView>(R.id.address).text = address
                findViewById<TextView>(R.id.updated_at).text = updatedAtText
                findViewById<TextView>(R.id.status).text = weatherDescription
                findViewById<TextView>(R.id.temp).text = temp + "°C"
                findViewById<TextView>(R.id.sunrise).text =
                    SimpleDateFormat("k:mm", Locale.ENGLISH).format(Date(sunrise * 1000))
                findViewById<TextView>(R.id.sunset).text =
                    SimpleDateFormat("k:mm", Locale.ENGLISH).format(Date(sunset * 1000))
                findViewById<TextView>(R.id.wind).text = windSpeed + " km/óra"
                findViewById<TextView>(R.id.pressure).text = pressure + " Pa"
                findViewById<TextView>(R.id.humidity).text = humidity + "%"

                /* Views populated, Hiding the loader, Showing the main design */
                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE

            } catch (e: Exception) {
                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                findViewById<TextView>(R.id.errorText).visibility = View.VISIBLE
            }

        }
    }
}