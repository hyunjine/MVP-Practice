package com.hyunjine.myapplication.data.repo

import com.hyunjine.myapplication.data.retrofit.Service

class Repository(private val api: Service) {
    fun getAnyCampingSiteInfo() = api.getBaseList()
}