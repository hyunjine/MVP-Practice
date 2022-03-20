package com.hyunjine.myapplication.presenter

import com.hyunjine.myapplication.data.repo.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainPresenter(
    private var view: MainContract.View? = null,
    private val repository: Repository
) :
    MainContract.Presenter {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onDetach() {
        if (view != null) view = null
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }

    override fun getAnyCampingSiteInfo() {
        addDisposable(repository.getAnyCampingSiteInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val data = it.response.body.items.item[0]
                    view?.onLoadCampingSiteInfo(data.facltNm, data.firstImageUrl)
                },
                {
                    it.message?.let { msg ->
                        view?.onFailLoad(msg)
                    }
                }
            ))
    }

    private fun addDisposable(disposable: Disposable) = compositeDisposable.add(disposable)
}