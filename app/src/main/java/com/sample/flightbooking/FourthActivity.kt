package com.sample.flightbooking

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONException
import org.json.JSONObject

class FourthActivity : AppCompatActivity() {

    private lateinit var Name : TextView
    private lateinit var From : TextView
    private lateinit var To : TextView
    private lateinit var From_time : TextView
    private lateinit var To_time : TextView
    private lateinit var Date : TextView
    private lateinit var Terminal : TextView
    private lateinit var Check_In_Time : TextView
    private lateinit var Gate : TextView
    private lateinit var Flight_Class : TextView
    private lateinit var Flight_Number : TextView
    private lateinit var Check_In_Number : TextView
    private lateinit var Price : TextView
    private lateinit var backPress : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)

        Name = findViewById(R.id.tv_1)
        From = findViewById(R.id.tv_2)
        To = findViewById(R.id.tv_3)
        From_time = findViewById(R.id.tv_4)
        To_time = findViewById(R.id.tv_5)
        Date = findViewById(R.id.tv_6)
        Terminal = findViewById(R.id.tv_7)
        Check_In_Time = findViewById(R.id.tv_8)
        Gate = findViewById(R.id.tv_9)
        Flight_Class = findViewById(R.id.tv_10)
        Flight_Number = findViewById(R.id.tv_11)
        Check_In_Number = findViewById(R.id.tv_12)
        Price = findViewById(R.id.tv_13)
        backPress = findViewById(R.id.imageView_back)

        backPress.setOnClickListener {
            finish()
        }


        val JsonData = intent.getStringExtra("Data")

        Log.d("oneData", "Json: $JsonData")

        if (JsonData != null) {
            try {
                // Parse the JSON string to a Passenger object
                val gson = Gson()
//                val data = gson.fromJson(JsonData, Passenger::class.java)
//                Log.d("Data", "Json: $data")

                val dataClass: Passenger = gson.fromJson(JsonData, Passenger::class.java)
                // Display a Toast with the passenger's name
               Log.d( "FlightClass",dataClass.flight_class.toString())
                Toast.makeText(this, dataClass.name, Toast.LENGTH_SHORT).show()

                // Extract and set the individual fields to the TextViews
                Name.text = "Name: ${dataClass.name}"
                From.text = "From: ${dataClass.from}"
                To.text = "To: ${dataClass.to}"
                From_time.text = "From Time: ${dataClass.from_time}"
                To_time.text = "To Time: ${dataClass.to_time}"
                Date.text = "Date: ${dataClass.date}"
                Terminal.text = "Terminal: ${dataClass.terminal}"
                Check_In_Time.text = "Check-In Time: ${dataClass.check_in_time}"
                Gate.text = "Gate: ${dataClass.gate}"
                Flight_Class.text = "Flight Class: ${dataClass.flight_class}"
                Flight_Number.text = "Flight Number: ${dataClass.flight_number}"
                Check_In_Number.text = "Check-In Number: ${dataClass.check_in_number}"
                Price.text = "Price: ${dataClass.price}"

            } catch (e: Exception) {
                Toast.makeText(this, "Error parsing JSON: ${e.message}", Toast.LENGTH_SHORT).show()
                Log.e("Exception", "Exception: ${e.message}")
            }
        } else {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show()
        }
    }
//        Log.d("jsonData","Json : $JsonData")
//
//        try {
//            val gson=Gson()
//            val data=gson.fromJson(JsonData.toString(),Passenger::class.java)
//            Log.d("Data","Json : $data")
//            Toast.makeText(this,data.name,Toast.LENGTH_SHORT).show()
//        } catch (e: Exception){
//            Toast.makeText(this,e.message,Toast.LENGTH_SHORT).show()
//            Log.e("Exception ", "Exception : $e.message")
//        }
//
////        val jsonElement = JsonParser.parseString(JsonData)
////        val jsonObject: JsonObject = jsonElement.asJsonObject
//
//        // Extract individual fields
//        val name = JsonData?.name
//        val from = JsonData?.from
//        val to = JsonData?.to
//        val from_time = JsonData?.from_time
//        val to_time = JsonData?.to_time
//        val date = JsonData?.date
//        val terminal = JsonData?.terminal
//        val check_in_time = JsonData?.check_in_time
//        val gate = JsonData?.gate
//        val flight_class = JsonData?.flightClass
//        val flight_number = JsonData?.flight_number
//        val check_in_number = JsonData?.check_in_number
//        val price = JsonData?.price
//
//// Set the values to the TextViews
//        Name.text = "Name: $name"
//        From.text = "From: $from"
//        To.text = "To: $to"
//        From_time.text = "From Time: $from_time"
//        To_time.text = "To Time: $to_time"
//        Date.text = "Date: $date"
//        Terminal.text = "Terminal: $terminal"
//        Check_In_Time.text = "Check-In Time: $check_in_time"
//        Gate.text = "Gate: $gate"
//        Flight_Class.text = "Flight Class: $flight_class"
//        Flight_Number.text = "Flight Number: $flight_number"
//        Check_In_Number.text = "Check-In Number: $check_in_number"
//        Price.text = "Price: $price"
//
//    }
}