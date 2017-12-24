package com.aumlogic.cultofmetatron.nativecalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View


class MainActivity : AppCompatActivity() {
    var entry: String = ""
    var calculatorState = CalculatorState()
    var lock : Boolean = false
    var pristine : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun getDisplayText(): String {
        return displayText.text.toString()
    }

    private fun pushDisplayText(num: String) {
        if (pristine) {
            pristine = false
            displayText.text = num
        } else {
            displayText.text = displayText.text.toString() + num
        }
    }

    fun onZero(view: View) {
        pushDisplayText("0")
    }


    fun onOne(view: View) {
        pushDisplayText("1")
    }

    fun onTwo(view: View) {
        pushDisplayText("2")
    }

    fun onThree(view: View) {
        pushDisplayText("3")
    }

    fun onFour(view: View) {
        pushDisplayText("4")
    }

    fun onFive(view: View) {
        pushDisplayText("5")
    }

    fun onSix(view: View) {
        pushDisplayText("6")
    }

    fun onSeven(view: View) {
        pushDisplayText("7")
    }

    fun onEight(view: View) {
        pushDisplayText("8")
    }

    fun onNine(view: View) {
        pushDisplayText("9")
    }

    fun onClear(view: View) {
        displayText.text = "0"
        calculatorState.clear()
        pristine = true
    }

    fun onEquals(view: View) {
        val parsedEntry: Float? = displayText.text.toString().toFloat()
        //println("the parsedEntry -> ", + parsedEntry)

        if (parsedEntry !== null && !pristine) {
            val value = calculatorState
                    .calculate()
                    .pushNumber(parsedEntry)
                    .getLatestCalculatedValue()

            println("the value: " + value)
            if (value !== null) {
                displayText.text = value.toString()
            } else {
                displayText.text = ""
            }
        }
        pristine = true

    }

    fun onPlus(view: View) {
        //get the parsed version
        if (!pristine) {
            val parsedEntry: Float? = displayText.text.toString().toFloat()
            if (parsedEntry !== null) {
                calculatorState
                        .calculate() // if there's two numbers, calculate them according to whatever was in there before
                        .pushOperator("+")
                        .pushNumber(parsedEntry)
            }
            pristine = true
            updateDisplay()
        } else {
            calculatorState.pushOperator("+")
        }
    }

    fun updateDisplay() {
        displayText.text = calculatorState.getLatestCalculatedValue().toString()
    }



}
