package com.example.tzandroid.mvp

import com.example.tzandroid.comon.BaseView
import com.example.tzandroid.room.specialty.SpecialtyModel
import com.example.tzandroid.room.workes.ModelWorkes
import com.example.tzandroid.room.workes.WorkesModel
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)

interface WorkingView : BaseView {
    fun getListWorking(workes:List<WorkesModel>)
}