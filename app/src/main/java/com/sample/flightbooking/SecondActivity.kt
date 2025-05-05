package com.sample.flightbooking

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondActivity : AppCompatActivity() {
    
    private lateinit var searchView : SearchView
    private lateinit var recyclerview : RecyclerView
    private lateinit var backPress : ImageView
    private lateinit var passengerAdapter: PassengerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        searchView = findViewById(R.id.searchView);
        recyclerview = findViewById(R.id.recyclerview1)
        backPress = findViewById(R.id.imageView_back)

        backPress.setOnClickListener {
            finish()
        }

        val passengers = listOf(
            Passenger(
                name = "Alice Johnson",
                from = "New York",
                to = "Los Angeles",
                from_time = "07:00 AM",
                to_time = "10:30 AM",
                date = "Monday, August 15, 2020",
                terminal = "T1",
                check_in_time = "05:30 AM",
                gate = "B2",
                flight_class = "Business",
                flight_number = "A9876",
                check_in_number = "CHK001",
                price = "$250"
            ),
            Passenger(
                name = "John Smith",
                from = "Chicago",
                to = "Miami",
                from_time = "09:15 AM",
                to_time = "01:45 PM",
                date = "Monday, August 15, 2020",
                terminal = "T2",
                check_in_time = "07:45 AM",
                gate = "C3",
                flight_class = "Economy",
                flight_number = "B5432",
                check_in_number = "CHK002",
                price = "$180"
            ),
            Passenger(
                name = "Emma Williams",
                from = "Dallas",
                to = "San Francisco",
                from_time = "11:00 AM",
                to_time = "02:30 PM",
                date = "Monday, August 15, 2020",
                terminal = "T1",
                check_in_time = "09:30 AM",
                gate = "A1",
                flight_class = "Economy",
                flight_number = "C1234",
                check_in_number = "CHK003",
                price = "$220"
            ),
            Passenger(
                name = "Liam Brown",
                from = "Seattle",
                to = "Denver",
                from_time = "06:00 AM",
                to_time = "09:00 AM",
                date = "Monday, August 15, 2020",
                terminal = "T2",
                check_in_time = "04:30 AM",
                gate = "B5",
                flight_class = "Economy",
                flight_number = "D5678",
                check_in_number = "CHK004",
                price = "$150"
            ),
            Passenger(
                name = "Olivia Davis",
                from = "Houston",
                to = "Phoenix",
                from_time = "03:00 PM",
                to_time = "05:45 PM",
                date = "Monday, August 15, 2020",
                terminal = "T1",
                check_in_time = "01:30 PM",
                gate = "A3",
                flight_class = "Business",
                flight_number = "E8765",
                check_in_number = "CHK005",
                price = "$200"
            ),
            Passenger(
                name = "Noah Wilson",
                from = "Atlanta",
                to = "Boston",
                from_time = "08:00 AM",
                to_time = "11:30 AM",
                date = "Monday, August 15, 2020",
                terminal = "T2",
                check_in_time = "06:30 AM",
                gate = "C1",
                flight_class = "Economy",
                flight_number = "F4321",
                check_in_number = "CHK006",
                price = "$230"
            ),
            Passenger(
                name = "Sophia Martinez",
                from = "Orlando",
                to = "Chicago",
                from_time = "01:00 PM",
                to_time = "03:45 PM",
                date = "Monday, August 15, 2020",
                terminal = "T1",
                check_in_time = "11:30 AM",
                gate = "A7",
                flight_class = "Economy",
                flight_number = "G6789",
                check_in_number = "CHK007",
                price = "$190"
            ),
            Passenger(
                name = "James Garcia",
                from = "Detroit",
                to = "Las Vegas",
                from_time = "10:00 AM",
                to_time = "01:30 PM",
                date = "Monday, August 15, 2020",
                terminal = "T2",
                check_in_time = "08:30 AM",
                gate = "B4",
                flight_class = "Economy",
                flight_number = "H8765",
                check_in_number = "CHK008",
                price = "$240"
            ),
            Passenger(
                name = "Mia Rodriguez",
                from = "Philadelphia",
                to = "Austin",
                from_time = "04:00 PM",
                to_time = "06:30 PM",
                date = "Monday, August 15, 2020",
                terminal = "T1",
                check_in_time = "02:30 PM",
                gate = "C2",
                flight_class = "Economy",
                flight_number = "I5432",
                check_in_number = "CHK009",
                price = "$210"
            ),
            Passenger(
                name = "Elijah Anderson",
                from = "San Diego",
                to = "Seattle",
                from_time = "07:00 PM",
                to_time = "09:45 PM",
                date = "Monday, August 15, 2020",
                terminal = "T2",
                check_in_time = "05:30 PM",
                gate = "B6",
                flight_class = "Business",
                flight_number = "J9876",
                check_in_number = "CHK010",
                price = "260"
            )
        )

        passengerAdapter = PassengerAdapter(this, passengers)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = passengerAdapter

//        recyclerview.layoutManager = LinearLayoutManager(this)
//        recyclerview.adapter = PassengerAdapter(this@SecondActivity, passengers)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false // Not needed in this case
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                passengerAdapter.filter(newText ?: "")
                return true
            }
        })

    }
}