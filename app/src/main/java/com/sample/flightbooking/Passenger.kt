package com.sample.flightbooking

import android.os.Parcel
import android.os.Parcelable

data class Passenger(
    val name: String,
    val from: String,
    val to: String,
    val from_time: String,
    val to_time: String,
    val date: String,
    val terminal: String,
    val check_in_time: String,
    val gate: String,
    val flight_class: String,
    val flight_number: String,
    val check_in_number: String,
    val price: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(from)
        parcel.writeString(to)
        parcel.writeString(from_time)
        parcel.writeString(to_time)
        parcel.writeString(date)
        parcel.writeString(terminal)
        parcel.writeString(check_in_time)
        parcel.writeString(gate)
        parcel.writeString(flight_class)
        parcel.writeString(flight_number)
        parcel.writeString(check_in_number)
        parcel.writeString(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Passenger> {
        override fun createFromParcel(parcel: Parcel): Passenger {
            return Passenger(parcel)
        }

        override fun newArray(size: Int): Array<Passenger?> {
            return arrayOfNulls(size)
        }
    }
}
