package com.hyunjine.myapplication.data.retrofit.vo

data class CampingData(
    val response: Response
)
data class Response(
    val body: Body,
    val header: Header
)

data class Body(
    val items: Items,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)

data class Header(
    val resultCode: String,
    val resultMsg: String
)

data class Items(
    val item: ArrayList<Item>
)

data class Item(
    val facltNm: String,
    val firstImageUrl: String
)