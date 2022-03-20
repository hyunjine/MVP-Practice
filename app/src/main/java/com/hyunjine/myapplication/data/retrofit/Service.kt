package com.hyunjine.myapplication.data.retrofit

import com.hyunjine.myapplication.data.retrofit.vo.CampingData
import io.reactivex.Single
import retrofit2.http.GET

interface Service {
    @GET("basedList")
    fun getBaseList(): Single<CampingData>
}