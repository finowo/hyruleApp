package com.hyrule.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hyrule.app.NEW_HYRULE_ID
import java.util.*

@Entity(tableName = "entities")
data class HyruleEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var date: Date,
    var text: String
) {
    constructor() : this(NEW_HYRULE_ID, Date(), "")
    constructor(date: Date, text: String) : this(NEW_HYRULE_ID, date, text)
}
