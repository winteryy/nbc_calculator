package com.winterry.nbc_calculator

import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Stack

class Calculator private constructor() {

    private val addOperation = AddOperation()
    private val substractOperation = SubstractOperation()
    private val multiplyOperation = MultiplyOperation()
    private val divideOperation = DivideOperation()
    private val moduloOperation = ModuloOperation()
    private val converter = PostfixConverter()

    companion object {
        private var instance: Calculator? = null
        private val OPERATORS = listOf("+", "-", "*", "/", "%")
        private const val EXIT = "exit"
        private const val CLEAR = "clear"

        fun getInstance(): Calculator {
            return instance ?: synchronized(this) {
                instance ?: Calculator().also {
                    instance = it
                }
            }
        }
    }

    fun execute() {
        println("[계산기]\n입력가능 연산자: +, -, *, /, %, =, (, )\n입력가능 수: +-2^Integer.MAX_VALUE 범위 내의 실수(결과는 소수점 3자리까지 표기)\n입력 가능 커맨드: clear -> 식을 초기화합니다. exit -> 계산기를 종료합니다.\n사용 예: (입력, 32입력, -입력, 17.5입력, )입력, *입력, -3.2입력, =입력")
        printDivideLine()
        while(true) {
            val originExpression = inputExpression()
            if(originExpression[0] == EXIT) {
                printDivideLine()
                break
            }
            if(originExpression[0] == CLEAR) {
                printDivideLine()
                continue
            }

            postfixCalc(converter.convertToPostfix(originExpression))?.let {
                println("\n[연산결과]: $it")
            } ?: println("\n0으로 나누는 수식이 존재하거나, 표현할 수 없는 범위의 수식입니다.")
            printDivideLine()
        }


    }

    private fun postfixCalc(convertedExpression: List<String>): BigDecimal? {
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

    private fun inputExpression(): List<String> {
        println("\n새로운 수식 입력을 시작합니다.")
        var cmd: String
        var isValid: Boolean

        var leftBracket = 0 //여는 괄호 카운트
        var rightBracket = 0 //닫는 괄호 카운트
        var numFlag = true //숫자 입력 가능 플래그
        var opFlag = false //연산자 입력 가능 플래그

        val expression = mutableListOf<String>()

        while(true) {
            print("\n[현재 수식]: ")
            if(expression.size==0) print("None") else expression.forEach { print(it) }
            println("\n숫자나 연산자 혹은 커맨드를 입력하세요.")

            cmd = readln()
            if(cmd == EXIT) {
                println("계산을 종료합니다.")
                return listOf(EXIT)
            }else if(cmd == CLEAR) {
                println("수식을 초기화합니다.")
                return listOf(CLEAR)
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

    private fun printDivideLine() {
        println("================================================")
    }
}