package com.example.simplecalc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simplecalc.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var value: String = ""
    private var valueToBeComputed = 0.0
    private var computationSelected = "none"
    private val format = DecimalFormat("0.#########")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.numberInput.setText(value)

        setNumericButtons()
        setComputationButtons()
    }
    private fun numberPress(passedValue: String) {
        value = "${value}$passedValue"
        binding.numberInput.setText(value)
        if (computationSelected != "none") {
            "${format.format(valueToBeComputed)} $computationSelected $value"
        } else {
            value
        }
    }

    private fun setNumericButtons() {
        binding.btn0.setOnClickListener {
            numberPress("0")
        }

        binding.btn1.setOnClickListener {
            numberPress("1")
        }

        binding.btn2.setOnClickListener {
            numberPress("2")
        }

        binding.btn3.setOnClickListener {
            numberPress("3")
        }

        binding.btn4.setOnClickListener {
            numberPress("4")
            //value = "${value}4"

            //if (computationSelected != "none") {
             //   binding.numberInput.setText("${format.format(valueToBeComputed)} $computationSelected $value")
            //} else {
              //  binding.numberInput.setText(value)
            //}

            //binding.numberInput.setText(
            //    if (computationSelected != "none") {
            //        "${format.format(valueToBeComputed)} $computationSelected $value"
            //    } else {
            //        value
            //    }
            //)
        }

        binding.btn5.setOnClickListener {
            numberPress("5")
        }

        binding.btn6.setOnClickListener {
            numberPress("6")
        }

        binding.btn7.setOnClickListener {
            numberPress("7")
        }

        binding.btn8.setOnClickListener {
            numberPress("8")
        }

        binding.btn9.setOnClickListener {
            numberPress("9")
        }

    }

    private fun setComputationButtons() {
         binding.btnDivision.setOnClickListener { appendCharNoDups("/" )
            computationSelected = "/"
            valueToBeComputed = value.toDouble()
            value = ""
            //binding.numberInput.setText(value)
            //binding.numberInput.setText("${format.format(valueToBeComputed)} $computationSelected $value")
        }

        binding.btnMultiply.setOnClickListener { appendCharNoDups("*")
            computationSelected = "*"
            valueToBeComputed = value.toDouble()
            value = ""
            //binding.numberInput.setText("${format.format(valueToBeComputed)} $computationSelected $value")

        }

        binding.btnSubtract.setOnClickListener { appendCharNoDups("-")
            computationSelected = "-"
            valueToBeComputed = value.toDouble()
            value = ""
            //binding.numberInput.setText("${format.format(valueToBeComputed)} $computationSelected $value")
        }

        binding.btnAdd.setOnClickListener { appendCharNoDups("+")
            computationSelected = "+"
            valueToBeComputed = value.toDouble()
            value = ""
            //binding.numberInput.setText("${format.format(valueToBeComputed)} $computationSelected $value")
        }



        binding.btnDot.setOnClickListener {
            // dynamic if statement.
            // return @setOnClick will bring it out
            if (value.contains(".")) return@setOnClickListener
            value = "${value}."
            binding.numberInput.setText(value)
        }

        binding.btnAC.setOnClickListener {
            value = ""
            binding.numberInput.setText(value)
            binding.resultInput.text = value
            computationSelected = "none"
        }

        binding.btnDel.setOnClickListener {
            //dropLast take away previous (Int input base on how many want to backspace)
            value = value.dropLast(1)
            binding.numberInput.setText(value)
        }

        binding.btnEqual.setOnClickListener {
            val computedValue =
                when (computationSelected) {
                    "/" -> valueToBeComputed / value.toDouble()
                    "*" -> valueToBeComputed * value.toDouble()
                    "-" -> valueToBeComputed - value.toDouble()
                    "+" -> valueToBeComputed + value.toDouble()
                    else -> {
                        // edge case that handles computationSelected == "none"
                        Snackbar.make(
                            binding.root.rootView,
                            "Please choose a number",
                            Snackbar.LENGTH_LONG
                        ).show()
                        return@setOnClickListener
                    }
                }
            binding.resultInput.text = format.format(computedValue).toString()
        }
    }
    private fun appendCharNoDups(operator: String) {
        val lastChar = binding.toString().lastOrNull()
        if (lastChar != '+' || lastChar != '-' || lastChar != '*' || lastChar != '/') return
        value += operator
        binding.numberInput.setText(value)
    }
}