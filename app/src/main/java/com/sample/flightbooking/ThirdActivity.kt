package com.sample.flightbooking

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.JsonObject
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import org.json.JSONObject

class ThirdActivity : AppCompatActivity() {

    private lateinit var fromPlace : TextView
    private lateinit var fromPlaceTime : TextView
    private lateinit var toPlace : TextView
    private lateinit var toPlaceTime : TextView
    private lateinit var date : TextView
    private lateinit var passengerName : TextView
    private lateinit var terminal : TextView
    private lateinit var checkIn : TextView
    private lateinit var gate : TextView
    private lateinit var flightClass : TextView
    private lateinit var flightNumber : TextView
    private lateinit var checkInNumber : TextView
    private lateinit var qrCode : ImageView
    private lateinit var backPress : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        fromPlace = findViewById(R.id.tv_from)
        fromPlaceTime = findViewById(R.id.tv_fromtime)
        toPlace = findViewById(R.id.tv_to)
        toPlaceTime = findViewById(R.id.tv_totime)
        date = findViewById(R.id.tv_2)
        passengerName = findViewById(R.id.tv_4)
        terminal = findViewById(R.id.tv_8)
        checkIn = findViewById(R.id.tv_9)
        gate = findViewById(R.id.tv_10)
        flightClass = findViewById(R.id.tv_12)
        flightNumber = findViewById(R.id.tv_13)
        checkInNumber = findViewById(R.id.tv_14)
        qrCode = findViewById(R.id.ivQrCode)
        backPress = findViewById(R.id.imageView_back)

        backPress.setOnClickListener {
            finish()
        }

        // Retrieve data from the intent
        val passenger  = intent.getParcelableExtra<Passenger>("data")
        Log.d("result",passenger.toString())
        passengerName.text = passenger?.name
        fromPlace.text = passenger?.from
        toPlace.text = passenger?.to
        fromPlaceTime.text = passenger?.from_time
        toPlaceTime.text = passenger?.to_time
        date.text = passenger?.date
        terminal.text = passenger?.terminal
        checkIn.text = passenger?.check_in_time
        gate.text = passenger?.gate
        flightClass.text = passenger?.flight_class
        flightNumber.text = passenger?.flight_number
        checkInNumber.text = passenger?.check_in_number

        val qrData = """
            name: ${passenger?.name}
            from: ${passenger?.from}
            to: ${passenger?.to}
            from_time: ${passenger?.from_time}
            to_time: ${passenger?.to_time}
            date: ${passenger?.date}
            terminal: ${passenger?.terminal}
            check_in_time: ${passenger?.check_in_time}
            gate: ${passenger?.gate}
            flight_class: ${passenger?.flight_class}
            flight_number: ${passenger?.flight_number}
            check_in_number: ${passenger?.check_in_number}
            price:${passenger?.price}
        """.trimIndent()

        Log.d("sampleDate","name : ${passenger?.name}")
        Log.d("flightClass",passenger?.flight_class.toString())

        // Generate QR Code
        Log.d("qrData", "qrData : $qrData")
        val jsonObject = convertToJSON(qrData)
        generateQRCode(jsonObject.toString())
    }

    private fun generateQRCode(data: String) {
        try {
            val qrCodeWriter = QRCodeWriter()
            val bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) android.graphics.Color.BLACK else android.graphics.Color.WHITE)
                }
            }

            qrCode.setImageBitmap(bitmap)

        } catch (e: Exception) {
            Log.e("QRCode", "Error generating QR Code: ${e.message}")
        }
    }

    private fun convertToJSON(input : String) : JSONObject {
        val json = JSONObject()
        input.split("\n").forEach { line ->
            val parts = line.split(":", limit = 2)
            if (parts.size == 2) {
                val key = parts[0].trim()
                val value = parts[1].trim()
                json.put(key, value)
            }
        }
        return json
    }
}