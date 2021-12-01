package com.hyrule.app

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hyrule.app.data.AppDatabase
import com.hyrule.app.data.EntityDao
import com.hyrule.app.data.HyruleEntity
import com.hyrule.app.data.SampleDataProvider
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var dao: EntityDao
    private lateinit var database: AppDatabase

    @Before
    fun createDb() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.entityDao()!!
    }


    @Test
    fun createEntities() {
        dao.insertALl(SampleDataProvider.getHyruleEntities())
        val count = dao.getCount()
        assertEquals(count, SampleDataProvider.getHyruleEntities())
    }

    @Test
    fun insertEntity(){
        val entity = HyruleEntity()
        entity.text = "brgusdigbnuri"
        dao.insertEntity(entity)
        val savedEntity = dao.getEntityById(1)
        //assertEquals(savedEntity?.id ?: 0, 1)
    }

    @After
    fun closeDb() {
        database.close()
    }

}