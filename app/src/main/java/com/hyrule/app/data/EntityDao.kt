package com.hyrule.app.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntity(entity: HyruleEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertALl(entities: List<HyruleEntity>)

    @Query("SELECT * FROM entities")
    fun getAll(): LiveData<List<HyruleEntity>>

    @Query("SELECT * FROM entities WHERE id = :id")
    fun getEntityById(id: Int): HyruleEntity?

    @Query("SELECT COUNT(*) from entities")
    fun getCount(): Int

    @Delete
    fun deleteEntities(selectedEntities: List<HyruleEntity>): Int

    @Query("DELETE FROM entities")
    fun deleteAll(): Int

    @Delete
    fun deleteEntity(entity: HyruleEntity)
}