package com.hyrule.app.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hyrule.app.NEW_HYRULE_ID
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "entities")
data class HyruleEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var date: Date,
    var text: String
) : Parcelable {
    constructor() : this(NEW_HYRULE_ID, Date(), "")
    constructor(date: Date, text: String) : this(NEW_HYRULE_ID, date, text)
}
