package com.aumlogic.cultofmetatron.nativecalculator

import java.util.Stack

/**
 * Created by cultofmetatron on 12/22/17.
 */
class CalculatorState {
    var stack = Stack<Float>()
    var operator: String? = null

    fun getLatestCalculatedValue() : Float {
        calculate()
        return stack.peek()
    }


    fun calculate() : CalculatorState {
        when (stack.count()) {
            2 -> {
                // pull both out and operate according to the operand
                val op2: Float = if (stack.peek() !== null) stack.pop() else 0.toFloat()
                val op1: Float = if (stack.peek() !== null) stack.pop() else 0.toFloat()

                when (true) {
                    operator.equals("+") -> {
                        val value: Float
                        value = op1 + op2
                        stack.push(value)
                        operator = null
                    }
                    operator.equals("-") -> {
                        val value: Float
                        value = op1 - op2
                        stack.push(value)
                        operator = null
                    }
                    operator.equals("/") -> {
                        //handle 0 case later
                        val value: Float = op1 / op2
                        stack.push(value)
                        operator = null
                    } else -> null // do nothing
                }


                return this
            }
            else ->
                return this

        }
    }

    fun pushOperator(op: String): CalculatorState {
        operator = op
        return this
    }



    //push a number onto the stack
    fun pushNumber(number: Float): CalculatorState {
        stack.push(number)
        calculate() //recalculate
        return this
    }

}