package com.example.tzandroid.mvp

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.example.tzandroid.comon.BasePresenter
import com.example.tzandroid.room.WorkesDB
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState

@InjectViewState
@SuppressLint("CheckResult")
class WorkingPresenter() : BasePresenter<WorkingView>() {
    fun readWorking(context: Context, idSpecialty: Long) {
    WorkesDB.getAppDateBase(context)!!.getWorkesDao().getWorking(idSpecialty)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
                viewState.getListWorking(it)
        }, {
            Log.d("kotlinError", it.message.toString())
        }).autoDisposable()
    }
}