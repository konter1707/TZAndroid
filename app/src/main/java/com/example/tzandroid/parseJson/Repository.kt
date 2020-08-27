package com.example.tzandroid.parseJson

import io.reactivex.Single

class Repository(private val serverApi:ServerApi) {
    fun repository():Single<Result>{
        return serverApi.moodel()
    }
}