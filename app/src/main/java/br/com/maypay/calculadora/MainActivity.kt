package br.com.maypay.calculadora

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.mariuszgromada.math.mxparser.Expression
import kotlin.math.exp


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator_layout)

        /* Number Buttons */
        var btnZero = findViewById<Button>(R.id.btnZero)
        var btnOne = findViewById<Button>(R.id.btnOne)
        var btnTwo = findViewById<Button>(R.id.btnTwo)
        var btnThree = findViewById<Button>(R.id.btnThree)
        var btnFour = findViewById<Button>(R.id.btnFour)
        var btnFive = findViewById<Button>(R.id.btnFive)
        var btnSix = findViewById<Button>(R.id.btnSix)
        var btnSeven = findViewById<Button>(R.id.btnSeven)
        var btnEight = findViewById<Button>(R.id.btnEight)
        var btnNine = findViewById<Button>(R.id.btnNine)

        var btnDecimal = findViewById<Button>(R.id.btnDecimal)
        var btnDoubleZero = findViewById<Button>(R.id.btnDoubleZero)

        /* Operator Buttons */
        var btnClear = findViewById<Button>(R.id.btnClear);
        var btnPercentage = findViewById<Button>(R.id.btnPercentage);
        var btnDivide = findViewById<Button>(R.id.btnDivide);
        var btnMultiply = findViewById<Button>(R.id.btnMultiply);
        var btnMinus = findViewById<Button>(R.id.btnMinus);
        var btnAdd = findViewById<Button>(R.id.btnAdd);
        var btnEqual = findViewById<Button>(R.id.btnEqual);

        var tvExpression = findViewById<TextView>(R.id.tvExpression);
        var tvResult = findViewById<TextView>(R.id.tvResult);

        btnClear.setOnClickListener { clearScreen(tvExpression, tvResult) }

        btnEqual.setOnClickListener { showResult(tvExpression, tvResult) }

        btnOne.setOnClickListener { writeExpression("1", tvExpression) }
        btnTwo.setOnClickListener { writeExpression("2", tvExpression) }
        btnThree.setOnClickListener { writeExpression("3", tvExpression) }
        btnFour.setOnClickListener { writeExpression("4", tvExpression) }
        btnFive.setOnClickListener { writeExpression("5", tvExpression) }
        btnSix.setOnClickListener { writeExpression("6", tvExpression) }
        btnSeven.setOnClickListener { writeExpression("7", tvExpression) }
        btnEight.setOnClickListener { writeExpression("8", tvExpression) }
        btnNine.setOnClickListener { writeExpression("9", tvExpression) }
        btnZero.setOnClickListener { writeExpression("0", tvExpression) }
        btnDoubleZero.setOnClickListener { writeExpression("00", tvExpression) }
        btnDecimal.setOnClickListener { writeExpression(".", tvExpression) }

        btnPercentage.setOnClickListener { writeExpression("*[%]", tvExpression) }
        btnDivide.setOnClickListener { writeExpression("/", tvExpression) }
        btnMultiply.setOnClickListener { writeExpression("*", tvExpression) }
        btnMinus.setOnClickListener { writeExpression("-", tvExpression) }
        btnAdd.setOnClickListener { writeExpression("+", tvExpression) }

    }

    fun percentage(value: String, tvExpression: TextView){

    }

    fun writeExpression(value: String, tvExpression: TextView) {
        var expression: String = tvExpression.text.toString()
        expression += value
        tvExpression.text = expression
    }

    private fun clearScreen(tvExpression: TextView, tvResult: TextView) {
        tvExpression.text = ""
        tvResult.text = "0"
    }

    private fun showResult(tvExpression: TextView, tvResult: TextView) {
        val expression = tvExpression.text.toString()
        val e = Expression(expression)
        println(expression)
        println(e.calculate().toString())
        val result = e.calculate().toString()
        tvResult.text = result
    }
}