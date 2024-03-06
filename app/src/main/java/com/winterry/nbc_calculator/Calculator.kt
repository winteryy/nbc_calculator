package com.winterry.nbc_calculator

import java.math.BigDecimal

class Calculator private constructor() {

    private lateinit var addOperation: AddOperation
    private lateinit var substractOperation: SubstractOperation
    private lateinit var multiplyOperation: MultiplyOperation
    private lateinit var divideOperation: DivideOperation
    private val converter = PostfixConverter()

    companion object {
        private var instance: Calculator? = null

        private val OPERATORS = listOf("+", "-", "*", "/")

        fun getInstance(): Calculator {
            return instance ?: synchronized(this) {
                instance ?: Calculator().also {
                    instance = it
                }
            }
        }
    }

    fun calc() {

        inputExpression()?.let {
            println(converter.convertToPostfix(it))
        } ?: return

    }

    private fun inputExpression(): List<String>? {
        println("수식 입력을 시작합니다.")
        var cmd: String
        var leftBracket = 0
        var rightBracket = 0

        var numFlag = true
        var opFlag = false
        var isValid = true
        val expression = mutableListOf<String>()

        while(true) {
            println("\n숫자 혹은 연산자를 입력하세요.")
            print("현재 수식: ")
            if(expression.size==0) print("None") else expression.forEach { print(it) }
            print("\n")

            cmd = readln()
            if(cmd == "exit") {
                println("계산을 종료합니다.")
                return null
            }

            when(cmd) {
                in OPERATORS -> {
                    isValid = if(opFlag) {
                        expression.add(cmd)

                        numFlag = true
                        opFlag = false
                        true
                    } else {
                        false
                    }
                }

                "(" -> {
                    isValid = if(numFlag) {
                        expression.add(cmd)
                        leftBracket++

                        true
                    } else {
                        false
                    }
                }

                ")" -> {
                    isValid = if(opFlag && leftBracket>rightBracket) {
                        expression.add(cmd)
                        rightBracket++

                        true
                    } else {
                        false
                    }
                }

                "=" -> {
                    isValid = if(opFlag && leftBracket==rightBracket) {
                        return expression
                    }else {
                        false
                    }
                }

                else -> {
                    isValid = if(numFlag) {
                        try {
                            BigDecimal(cmd)
                            expression.add(cmd)

                            numFlag = false
                            opFlag = true
                            true
                        } catch (e: Exception) {
                            false
                        }
                    } else {
                        false
                    }
                }
            }

            if(!isValid) {
                println("잘못된 수식입니다.")
            }
        }
    }

}