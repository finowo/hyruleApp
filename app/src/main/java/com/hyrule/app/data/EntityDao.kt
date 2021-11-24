package com.hyrule.app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntity(entity: HyruleEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertALl(entities: List<HyruleEntity>)

    @Query("SELECT * FROM entities")
    fun getAll(): LiveData<List<HyruleEntity>>

    @Query("SELECT * FROM entities WHERE id = :id")
    fun getEntityById(id: Int)
}