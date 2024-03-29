package com.aochdjp.playwithgura_san.model.repositories

import com.aochdjp.playwithgura_san.model.Menu
import com.aochdjp.playwithgura_san.model.repositories.abstracts.ApiFactory
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

val menuApi: MenuApi
    get() = ApiFactory.retrofit!!.create(MenuApi::class.java)

interface MenuApi {
    @GET("api/menu/")
    fun menu(@Query("v") version: String): Observable<Menu>
}