package com.sample.flightbooking

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PassengerAdapter(private val context: Context,private val passengers: List<Passenger>) :
    RecyclerView.Adapter<PassengerAdapter.PassengerViewHolder>() {

    private var filteredPassengers: MutableList<Passenger> = passengers.toMutableList()

    class PassengerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val areaFrom: TextView = itemView.findViewById(R.id.tv_from)
        val areaFromTime: TextView = itemView.findViewById(R.id.tv_fromtime)
        val areaTo: TextView = itemView.findViewById(R.id.tv_to)
        val areaToTime: TextView = itemView.findViewById(R.id.tv_totime)
        val flightPrice: TextView = itemView.findViewById(R.id.tv_price)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassengerViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.carditem,parent,false)
        return PassengerViewHolder(item)
    }

    override fun getItemCount(): Int {
        return filteredPassengers.size
    }

    override fun onBindViewHolder(holder: PassengerViewHolder, position: Int) {
        val passenger = filteredPassengers[position]
        holder.areaFrom.text = passenger.from
        holder.areaFromTime.text = passenger.from_time
        holder.areaTo.text = passenger.to
        holder.areaToTime.text = passenger.to_time
        holder.flightPrice.text = passenger.price
        holder.itemView.setOnClickListener {
            val intent = Intent(context,ThirdActivity::class.java)
            intent.putExtra("data", passenger)
            Log.d("passenger", "passenger detail : $passenger")

            context.startActivity(intent)
        }
    }

    fun filter(query: String) {
        val lowerCaseQuery = query.lowercase()
        filteredPassengers = if (query.isEmpty()) {
            passengers.toMutableList()
        } else {
            passengers.filter {
                it.name.lowercase().contains(lowerCaseQuery) ||
                        it.from.lowercase().contains(lowerCaseQuery) ||
                        it.to.lowercase().contains(lowerCaseQuery)
            }.toMutableList()
        }
        notifyDataSetChanged()
    }
}