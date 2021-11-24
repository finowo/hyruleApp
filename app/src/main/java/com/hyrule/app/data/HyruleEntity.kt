package com.hyrule.app.data

import com.hyrule.app.NEW_HYRULE_ID
import java.util.*

data class HyruleEntity(
    var id: Int,
    var date: Date,
    var text: String
) {
    constructor() : this(NEW_HYRULE_ID, Date(), "")
    constructor(date: Date, text: String) : this(NEW_HYRULE_ID, date, text)
}
