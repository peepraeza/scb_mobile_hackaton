package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable


data class ProfileParcel(
    val citizenId: String?,
    val name: String?,
    val lastName: String?,
    val phoneNumber: String?,
    val gender: String?,
    val alias: String?,
    val point: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(citizenId)
        parcel.writeString(name)
        parcel.writeString(lastName)
        parcel.writeString(phoneNumber)
        parcel.writeString(gender)
        parcel.writeString(alias)
        parcel.writeString(point)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProfileParcel> {
        override fun createFromParcel(parcel: Parcel): ProfileParcel {
            return ProfileParcel(parcel)
        }

        override fun newArray(size: Int): Array<ProfileParcel?> {
            return arrayOfNulls(size)
        }
    }
}