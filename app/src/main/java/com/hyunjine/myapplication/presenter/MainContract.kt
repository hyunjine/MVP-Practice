package com.hyunjine.myapplication.presenter

interface MainContract {
    interface View {
        fun onLoadCampingSiteInfo(name: String, firstImageUrl: String)
        fun onFailLoad(errorMsg: String)
    }

    interface Presenter {
        fun onDetach()
        fun getAnyCampingSiteInfo()
    }
}