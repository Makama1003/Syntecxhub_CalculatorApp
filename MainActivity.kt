package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView

    private var currentInput = ""
    private var operator = ""
    private var firstNumber = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

        val buttonIds = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnAdd, R.id.btnSub, R.id.btnMul, R.id.btnDiv,
            R.id.btnEqual, R.id.btnClear
        )

        for (id in buttonIds) {
            findViewById<Button>(id).setOnClickListener {
                handleInput((it as Button).text.toString())
            }
        }
    }

    private fun handleInput(value: String) {
        when (value) {

            "0","1","2","3","4","5","6","7","8","9" -> {
                currentInput += value
                display.text = currentInput
            }

            "+", "-", "*", "/" -> {
                if (currentInput.isNotEmpty()) {
                    firstNumber = currentInput.toDouble()
                    operator = value
                    currentInput = ""
                }
            }

            "=" -> {
                if (currentInput.isNotEmpty()) {
                    val secondNumber = currentInput.toDouble()
                    val result = calculate(firstNumber, secondNumber, operator)
                    display.text = result
                    currentInput = result
                }
            }

            "C" -> reset()
        }
    }

    private fun calculate(a: Double, b: Double, op: String): String {
        return try {
            val result = when (op) {
                "+" -> a + b
                "-" -> a - b
                "*" -> a * b
                "/" -> {
                    if (b == 0.0) return "Error"
                    a / b
                }
                else -> 0.0
            }
            result.toString()
        } catch (e: Exception) {
            "Error"
        }
    }

    private fun reset() {
        currentInput = ""
        operator = ""
        firstNumber = 0.0
        display.text = "0"
    }
}