package com.example.tzandroid.comon

import java.text.SimpleDateFormat
import java.util.*

class EditingData {
    fun capitalizeOnlyFirstLatter(word: String): String {
        val w = word.toLowerCase()
        val capitalLetter =
            word[0].toString().replace(word.substring(0, 1), word.substring(0, 1).toUpperCase())
        val lowCase = w.substring(1, word.length)
        return capitalLetter + lowCase
    }

    fun formatDate(word: String): String {
        return if (word.substring(4, 5) == "-") {
            val df = SimpleDateFormat("yyyy-MM-dd")
            val output = SimpleDateFormat("dd-MM-yyyy")
            val date = df.parse(word)
            output.format(date).replace("-", ".")
        } else {
            word.replace("-", ".")
        }
    }
    fun getYear(birthday: String):String{
        if (birthday != "-"){
            val birthYear=birthday.split(".")[2].toInt()
            val calendar= Calendar.getInstance()
            val currentYear=calendar.get(Calendar.YEAR)
            val age=currentYear-birthYear
            return age.toString()+" лет"
        }else
            return "-"
    }

}