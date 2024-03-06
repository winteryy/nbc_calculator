package com.winterry.nbc_calculator

fun main() {

    val calculator = Calculator.getInstance()
    var s = "null"

    val a = 5
    val b = 6

    val result = { l:Int, r:Int -> l+r }

    println(result(a,b))
    println(add(a, b))

}

fun add(a: Int, b: Int) = a+b