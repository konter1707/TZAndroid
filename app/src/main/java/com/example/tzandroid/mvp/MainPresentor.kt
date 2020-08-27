package com.example.tzandroid.mvp

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.example.tzandroid.comon.BasePresenter
import com.example.tzandroid.comon.EditingData
import com.example.tzandroid.parseJson.Repository
import com.example.tzandroid.parseJson.ServerApi
import com.example.tzandroid.room.WorkesDB
import com.example.tzandroid.room.specialty.SpecialtyModel
import com.example.tzandroid.room.workes.WorkesModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState

@InjectViewState
@SuppressLint("CheckResult")
class MainPresentor : BasePresenter<MainView>() {
    fun getResultGson(context: Context) {
        Repository(ServerApi.create()).repository()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                var fName: String = ""
                var lName: String = ""
                var avatarUrl: String = ""
                var birth: String = ""
                for (result in it.response) {
                    fName = EditingData().capitalizeOnlyFirstLatter(result.f_name)
                    lName = EditingData().capitalizeOnlyFirstLatter(result.l_name)
                    birth = if (result.birthday != null && !result.birthday.isEmpty())
                        EditingData().formatDate(result.birthday)
                    else "-"
                    avatarUrl = if (result.avatr_url != null && !result.avatr_url.isEmpty())
                        result.avatr_url
                     else "-"

                    result.specialty.forEach {res->
                        val specialtyId = res.specialty_id
                        val workesModel = WorkesModel(0,specialtyId,fName, lName, birth, avatarUrl)
                        val speialtyModel=SpecialtyModel(0,res.name,specialtyId)
                            insertWorkingData(context, workesModel)
                            insertSpecialtyData(context,speialtyModel)
                    }
                }
                readSpecialty(context)
            }, {
                Log.d("kotlinError", it.message.toString())
            }).autoDisposable()
    }

    fun insertWorkingData(context: Context, workesModel: WorkesModel) {
        WorkesDB.getAppDateBase(context)!!.getWorkesDao().insertRx(workesModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            }, {
                viewState.showToast("Error")
            }).autoDisposable()
    }
    fun insertSpecialtyData(context: Context, specialtyModel: SpecialtyModel) {
        WorkesDB.getAppDateBase(context)!!.getSpecialtyDao().insertSpecialty(specialtyModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            }, {
                viewState.showToast("Error")
            }).autoDisposable()
    }
    fun readSpecialty(context: Context) {
        WorkesDB.getAppDateBase(context)!!.getSpecialtyDao().getSpecialty()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (!it.isEmpty()) {
                    viewState.setListWorking(it)
                    }
                else {
                    getResultGson(context)
                }
            }, {
                Log.d("kotlinError", it.message.toString())
            }).autoDisposable()
    }
}