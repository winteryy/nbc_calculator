package com.winterry.nbc_calculator

import java.util.Stack

class PostfixConverter {
    companion object {
        private val opListForConvert = listOf("+", "-", "*", "/", "(", ")")
    }

    fun convertToPostfix(expression: List<String>): List<String> {
        val convertedExpression = mutableListOf<String>()

        val opStack = Stack<String>()

        for(cmd in expression) {
            if(!isOp(cmd)) {
                //숫자
                convertedExpression.add(cmd)
            }else if(opStack.isEmpty()) {
                //연산자 스택 비었으면 바로 push
                opStack.push(cmd)
            }else {
                //연산자 스택 비어있지 않을 때
                if(cmd == "(") {
                    //여는 괄호일 때
                    opStack.push(cmd)
                }else if(cmd == ")") {
                    //닫는 괄호일 때
                    var stTop = ""
                    while(true) {
                        stTop = opStack.pop()
                        if(stTop == "(") break else convertedExpression.add(stTop)
                    }
                }else {
                    //일반 연산자일 때
                    if(getPriority(cmd)>getPriority(opStack.peek())) {
                        //스택의 top보다 우선순위 높은 경우
                        opStack.push(cmd)
                    }else {
                        //낮은 경우
                        while(opStack.isNotEmpty()) {
                            if(getPriority(cmd)<=getPriority(opStack.peek())) {
                                convertedExpression.add(opStack.pop())
                            }else {
                                break
                            }
                        }
                        opStack.push(cmd)
                    }
                }
            }

        }

        while(opStack.isNotEmpty()) {
            convertedExpression.add(opStack.pop())
        }

        return convertedExpression
    }

    private fun isOp(cmd: String): Boolean {
        return cmd in opListForConvert
    }

    private fun getPriority(op: String): Int {
        return when(op) {
            "*", "/" -> 3
            "+", "-" -> 2
            "(" -> 1
            else -> -1
        }
    }
}