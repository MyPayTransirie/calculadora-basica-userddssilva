package br.com.maypay.calculadora

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator_layout)

        /*btNumber0.setOnClickListener {
            tvDisplayCalc.text = tvDisplayCalc.text.toString() + "0"
        }
        btNumber1.setOnClickListener {
            tvDisplayCalc.text = tvDisplayCalc.text.toString() + "1"
        }
        btNumber2.setOnClickListener {
            tvDisplayCalc.text = tvDisplayCalc.text.toString() + "2"
        }
        btNumber3.setOnClickListener {
            tvDisplayCalc.text = tvDisplayCalc.text.toString() + "3"
        }
        btNumber4.setOnClickListener {
            tvDisplayCalc.text = tvDisplayCalc.text.toString() + "4"
        }
        btNumber5.setOnClickListener {
            tvDisplayCalc.text = tvDisplayCalc.text.toString() + "5"
        }
        btNumber6.setOnClickListener {
            tvDisplayCalc.text = tvDisplayCalc.text.toString() + "6"
        }
        btNumber7.setOnClickListener {
            tvDisplayCalc.text = tvDisplayCalc.text.toString() + "7"
        }
        btNumber8.setOnClickListener {
            tvDisplayCalc.text = tvDisplayCalc.text.toString() + "8"
        }
        btNumber9.setOnClickListener {
            tvDisplayCalc.text = tvDisplayCalc.text.toString() + "9"
        }
        btRightParenthesis.setOnClickListener {
            tvDisplayCalc.text = tvDisplayCalc.text.toString() + "("
        }
        btLeftParenthesis.setOnClickListener {
            tvDisplayCalc.text = tvDisplayCalc.text.toString() + ")"
        }
        btDiv.setOnClickListener {
            tvDisplayCalc.text = tvDisplayCalc.text.toString() + "/"
        }
        btDot.setOnClickListener {
            tvDisplayCalc.text = tvDisplayCalc.text.toString() + "*"
        }
        btSub.setOnClickListener {
            tvDisplayCalc.text = tvDisplayCalc.text.toString() + "-"
        }
        btSum.setOnClickListener {
            tvDisplayCalc.text = tvDisplayCalc.text.toString() + "+"
        }
        btEqual.setOnClickListener {
            println(message = eval(str = "((4 - 2^3 + 1) * -sqrt(3*3+4*4)) / 2"))
        }*/
    }


    fun eval(str: String): Double {
        return object : Any() {
            var pos = -1
            var ch = 0
            fun nextChar() {
                ch = if (++pos < str.length) str[pos].toInt() else -1
            }

            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.toInt()) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < str.length) throw RuntimeException("Unexpected: " + ch.toChar())
                return x
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor
            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    if (eat('+'.toInt())) x += parseTerm() // addition
                    else if (eat('-'.toInt())) x -= parseTerm() // subtraction
                    else return x
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    if (eat('*'.toInt())) x *= parseFactor() // multiplication
                    else if (eat('/'.toInt())) x /= parseFactor() // division
                    else return x
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.toInt())) return parseFactor() // unary plus
                if (eat('-'.toInt())) return -parseFactor() // unary minus
                var x: Double
                val startPos = pos
                if (eat('('.toInt())) { // parentheses
                    x = parseExpression()
                    eat(')'.toInt())
                } else if (ch >= '0'.toInt() && ch <= '9'.toInt() || ch == '.'.toInt()) { // numbers
                    while (ch >= '0'.toInt() && ch <= '9'.toInt() || ch == '.'.toInt()) nextChar()
                    x = str.substring(startPos, pos).toDouble()
                } else if (ch >= 'a'.toInt() && ch <= 'z'.toInt()) { // functions
                    while (ch >= 'a'.toInt() && ch <= 'z'.toInt()) nextChar()
                    val func = str.substring(startPos, pos)
                    x = parseFactor()
                    x =
                        if (func == "sqrt") Math.sqrt(x) else if (func == "sin") Math.sin(
                            Math.toRadians(
                                x
                            )
                        ) else if (func == "cos") Math.cos(
                            Math.toRadians(x)
                        ) else if (func == "tan") Math.tan(Math.toRadians(x)) else throw RuntimeException(
                            "Unknown function: $func"
                        )
                } else {
                    throw RuntimeException("Unexpected: " + ch.toChar())
                }
                if (eat('^'.toInt())) x = Math.pow(x, parseFactor()) // exponentiation
                return x
            }
        }.parse()
    }
}

/*f we are going to implement it then we can can use the below algorithm :--

While there are still tokens to be read in,

1.1 Get the next token. 1.2 If the token is:

1.2.1 A number: push it onto the value stack.

1.2.2 A variable: get its value, and push onto the value stack.

1.2.3 A left parenthesis: push it onto the operator stack.

1.2.4 A right parenthesis:

 1 While the thing on top of the operator stack is not a
   left parenthesis,
     1 Pop the operator from the operator stack.
     2 Pop the value stack twice, getting two operands.
     3 Apply the operator to the operands, in the correct order.
     4 Push the result onto the value stack.
 2 Pop the left parenthesis from the operator stack, and discard it.
1.2.5 An operator (call it thisOp):

 1 While the operator stack is not empty, and the top thing on the
   operator stack has the same or greater precedence as thisOp,
   1 Pop the operator from the operator stack.
   2 Pop the value stack twice, getting two operands.
   3 Apply the operator to the operands, in the correct order.
   4 Push the result onto the value stack.
 2 Push thisOp onto the operator stack.
While the operator stack is not empty, 1 Pop the operator from the operator stack. 2 Pop the value stack twice, getting two operands. 3 Apply the operator to the operands, in the correct order. 4 Push the result onto the value stack.

At this point the operator stack should be empty, and the value stack should have only one value in it, which is the final result.

share  edit  follow */