package com.sample.flightbooking

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var flight: TextView
    private lateinit var flightHotel: TextView
    private lateinit var car: TextView
    private lateinit var searchBtn: Button
    private lateinit var scannerBtn : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flight = findViewById(R.id.tv_flight)
        flightHotel = findViewById(R.id.tv_flight_hotel)
        car = findViewById(R.id.tv_cars)
        searchBtn = findViewById(R.id.btn_searchflights)
        scannerBtn = findViewById(R.id.imageview_scanner)

        val textViews = listOf(flight, flightHotel, car)
        for (textView in textViews) {
            textView.setOnClickListener { view ->
                highlightSelectedTextView(view as TextView, textViews)
            }
        }

        searchBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
        }

        scannerBtn.setOnClickListener {
            val integrator = IntentIntegrator(this)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
            integrator.setPrompt("Scan a QR code or Barcode")
            integrator.setCameraId(0) // Use a specific camera (0 for rear, 1 for front)
            integrator.setBeepEnabled(true)
            integrator.setBarcodeImageEnabled(true)
            integrator.setOrientationLocked(true)
            integrator.initiateScan()
        }
    }

    private fun highlightSelectedTextView(selectedTextView: TextView, allTextViews: List<TextView>) {
        for (textView in allTextViews) {
            // Reset all TextViews to default color and background
            textView.setBackgroundColor(Color.WHITE)
            textView.setTextColor(Color.BLACK)
        }

        // Create GradientDrawable for the selected TextView with rounded corners
        val drawable = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 55f // Equivalent to 15dp for rounded corners
            setColor(Color.parseColor("#6C529E")) // Highlight color
        }

        // Apply the drawable as background to the selected TextView
        selectedTextView.background = drawable
        selectedTextView.setTextColor(Color.WHITE) // Set text color to white for selected TextView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) {
                // Show the scanned result in a toast
                Log.d("resultView",result.toString())
                val scannedContent = result.contents
                Toast.makeText(this, "Scanned: $scannedContent", Toast.LENGTH_LONG).show()

                // Create an intent and pass JSON as a string
                val intent = Intent(this@MainActivity, FourthActivity::class.java)
                intent.putExtra("Data", scannedContent.toString())
//                Log.d("myData","$")

                startActivity(intent)
            } else {
                // Handle the case where no result is found
                Toast.makeText(this, "No result found", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}
