package com.winterry.nbc_calculator

import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Stack

class PostfixCalculator {
    private val addOperation = AddOperation()
    private val substractOperation = SubstractOperation()
    private val multiplyOperation = MultiplyOperation()
    private val divideOperation = DivideOperation()
    private val moduloOperation = ModuloOperation()

    fun postfixCalc(convertedExpression: List<String>): BigDecimal? {
        val numStack = Stack<BigDecimal>()

        for(cmd in convertedExpression) {
            when(cmd) {
                "+" -> {
                    val rValue = numStack.pop()
                    val lValue = numStack.pop()

                    numStack.push(addOperation.operate(lValue, rValue)?:return null)
                }
                "-" -> {
                    val rValue = numStack.pop()
                    val lValue = numStack.pop()

                    numStack.push(substractOperation.operate(lValue, rValue)?:return null)
                }
                "*" -> {
                    val rValue = numStack.pop()
                    val lValue = numStack.pop()

                    numStack.push(multiplyOperation.operate(lValue, rValue)?:return null)
                }
                "/" -> {
                    val rValue = numStack.pop()
                    val lValue = numStack.pop()

                    numStack.push(divideOperation.operate(lValue, rValue)?:return null)
                }
                "%" -> {
                    val rValue = numStack.pop()
                    val lValue = numStack.pop()

                    numStack.push(moduloOperation.operate(lValue, rValue)?:return null)
                }
                else -> {
                    numStack.push(BigDecimal(cmd))
                }
            }
        }

        return numStack.peek().setScale(3, RoundingMode.HALF_EVEN).stripTrailingZeros()
    }

}