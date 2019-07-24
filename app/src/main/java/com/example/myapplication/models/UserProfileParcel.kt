package com.example.myapplication.models

import android.os.Parcel
import android.os.Parcelable

data class UserProfileParcel(
    val firstname: String?,
    val lastName: String?,
    val phoneNumber: String?,
    val gender: String?,
    val citizenId: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(firstname)
        parcel.writeString(lastName)
        parcel.writeString(phoneNumber)
        parcel.writeString(gender)
        parcel.writeString(citizenId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserProfileParcel> {
        override fun createFromParcel(parcel: Parcel): UserProfileParcel {
            return UserProfileParcel(parcel)
        }

        override fun newArray(size: Int): Array<UserProfileParcel?> {
            return arrayOfNulls(size)
        }
    }
}
