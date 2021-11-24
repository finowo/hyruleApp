package com.hyrule.app.data

import java.util.*

class SampleDataProvider {

    companion object {
        private val sampleHyruleEntity1 = "Moblin"
        private val sampleHyruleEntity2 = "Lynel\n scary"
        private val sampleHyruleEntity3 = """
            Chuchus, also known as ChuChus, and Chus, are recurring Enemies in The Legend of Zelda series. They are gelatinous creatures of living slime or jelly, often depicted with facial features.
            Chuchus come in a wide variety of colors and types, with some having different traits and behaviors depending on their color. Red, Green, Blue, Yellow and Purple Chuchus are among some of the most common types of Chuchus in the series. Beginning with The Wind Waker, some Chuchus are capable of producing electricity as a defense mechanism, making them similar to Buzz Blobs in both appearance and behavior. Chuchus are relatively simple enemies to defeat; most varieties can be felled by a simple slash with the Swords, while some types must first be stunned with another item.

        """.trimIndent()
        private val sampleHyruleEntity4 = "Bokoblin"
        private val sampleHyruleEntity5 = "Lizalfos"


        private fun getDate(diff: Long): Date {
            return Date(Date().time + diff)
        }

        fun getHyruleEntities() = arrayListOf(
            HyruleEntity(1, getDate(0), sampleHyruleEntity1),
            HyruleEntity(2, getDate(1), sampleHyruleEntity2),
            HyruleEntity(3, getDate(2), sampleHyruleEntity3),
            HyruleEntity(4, getDate(3), sampleHyruleEntity4),
            HyruleEntity(5, getDate(4), sampleHyruleEntity5),
        )

    }

}