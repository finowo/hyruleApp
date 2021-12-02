package com.hyrule.app.data

import com.hyrule.app.NEW_HYRULE_ID
import android.os.Parcel
import android.os.Parcelable

// data means the class is going to have some properties, will have at least one primary constructor, and have functions such as equals() toString...
// See https://www.javatpoint.com/kotlin-data-class for a comparison of Java and Kotlin classes

data class HyruleEntity(
    // Note in this version of my code I've changed the attributes so they match up with the JSON data.
    var id: Int,
    var category: String?,
    var common_locations: String?,
    var description: String?,
    var drops: String?,
    var image: String?,
    var name: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HyruleEntity> {
        override fun createFromParcel(parcel: Parcel): HyruleEntity {
            return HyruleEntity(parcel)
        }

        override fun newArray(size: Int): Array<HyruleEntity?> {
            return arrayOfNulls(size)
        }
    }
}

