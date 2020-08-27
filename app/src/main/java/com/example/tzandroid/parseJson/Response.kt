package com.example.tzandroid.parseJson

data class Response(
    var f_name: String,
    var l_name: String,
    var birthday: String,
    var avatr_url: String,
var specialty: List<Specialty>
)
data class Specialty(var specialty_id:Long, var name:String)
data class Result(var response:List<Response>)
