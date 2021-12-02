package com.hyrule.app.dataaccess

import com.hyrule.app.data.HyruleEntity
import retrofit2.Response
import retrofit2.http.GET

interface HyruleCompendiumApi {
    @GET("category/monsters")
    suspend fun getHyruleEntities(): List<HyruleEntity>
}
