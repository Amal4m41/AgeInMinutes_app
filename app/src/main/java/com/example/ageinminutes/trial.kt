package com.example.ageinminutes

import java.text.SimpleDateFormat
import java.util.*

fun main() {
    val sdf= SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
    val d:Date=sdf.parse("3/1/2021")
    val d2:Date=sdf.parse("1/1/2021")
//    println((d.time-d2.time)/60000)

    println((d.time-d2.time)/(3600000*24))


}