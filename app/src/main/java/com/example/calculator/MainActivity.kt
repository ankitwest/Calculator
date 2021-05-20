package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    val btn11 = findViewById<Button>(R.id.btn1) as Button
    //val tv = findViewById<EditText>(R.id.tvInput) as EditText   //TextView

    fun onDigit(view : View) {
        //Toast.makeText(this,"Button Works ",Toast.LENGTH_LONG).show()
        tv.append((view as Button).text)
        lastNumeric=true

        if(tv.text.contains("1"))
            tv.text("ha")
    }

    fun onClr(view : View){
        tv.text =""
        lastNumeric = false
        lastDot  = false
    }
    fun onDecimal(view : View){
        if(lastNumeric && !lastDot) {
            tv.append(".")
            lastNumeric=false
            lastDot=true
        }
    }
    fun onOperator(view : View) {
        if (lastNumeric && !isOperatorAdded(tv.text.toString())) {
            tv.append((view as Button).text)
            lastNumeric = false // Update the flag
            lastDot = false    // Reset the DOT flag
        }
    }



    fun onEqu(view : View){
        if(lastNumeric){
            var value = tv.text.toString()
            var prefix = ""
            try {
                        if (value.startsWith("-")) {
                           prefix = "-"
                         value = value.substring(1);
                }


                if (value.contains("/")) {

                    val splitedValue = value.split("/")

                    var one = splitedValue[0] // Value One
                    val two = splitedValue[1] // Value Two

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    tv.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }


                else if (value.contains("*")) {

                    val splitedValue = value.split("*")

                    var one = splitedValue[0]
                    val two = splitedValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    tv.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }


                else if (value.contains("-")) {


                    val splitedValue = value.split("-")

                    var one = splitedValue[0]
                    val two = splitedValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    tv.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }

                else if (value.contains("+")) {

                    val splitedValue = value.split("+")

                    var one = splitedValue[0]
                    val two = splitedValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    tv.text =
                        removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())     //tvInput
                }
            }

            catch (e : ArithmeticException) {
                e.printStackTrace()
            }
        }
    }


    private fun removeZeroAfterDot(result: String): String {
        var value = result
        if (result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }
        return value
    }



    private fun isOperatorAdded(value: String): Boolean {

        return if (value.startsWith("-")) {
            false
        } else {
            (value.contains("/") || value.contains("*") || value.contains("-") || value.contains("+"))
        }
    }
}