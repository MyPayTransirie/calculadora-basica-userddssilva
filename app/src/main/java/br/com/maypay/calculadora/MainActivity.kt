package br.com.maypay.calculadora

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.screen.*
import kotlinx.android.synthetic.main.simple_cal_buttons.*
import org.mariuszgromada.math.mxparser.Expression


class MainActivity : AppCompatActivity() {

    private var historico = ArrayList<History>()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
           /* R.id.action_cut -> {
                //text_view.text = "Cut"
                return true
            }
            R.id.action_copy -> {
                //text_view.text = "Copy"
                return true
            }
            R.id.action_paste -> {
                //text_view.text = "Paste"
                return true
            }*/
            R.id.action_history -> {
                // instancing an intent
                var intent = Intent(this, HistActivity::class.java)

                // sending a simple string
                val title: String = "Histórico"
                intent.putExtra("Title", title)

                // sending an instance of History class
                val historic = History("13+4", "17")
                intent.putExtra("Historic", historic)

                // sending an array of Histories
                intent.putExtra("Historics", this.historico)

                // start historic activity
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator_layout)

        val toolbar: Toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        /* Number Buttons */
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

        /* Operator Buttons */
        btnPercentage.setOnClickListener { writeExpression("%", tvExpression) }
        btnDivide.setOnClickListener { writeExpression("/", tvExpression) }
        btnMultiply.setOnClickListener { writeExpression("*", tvExpression) }
        btnMinus.setOnClickListener { writeExpression("-", tvExpression) }
        btnAdd.setOnClickListener { writeExpression("+", tvExpression) }

        btnClear.setOnClickListener { clearScreen(tvExpression, tvResult) }
        btnEqual.setOnClickListener { showResult(tvExpression, tvResult) }

    }

    private fun writeExpression(value: String, tvExpression: TextView) {
        var expression: String = tvExpression.text.toString()
        expression += value
        tvExpression.text = expression
    }

    private fun clearScreen(tvExpression: TextView, tvResult: TextView) {
        tvExpression.text = ""
        tvResult.text = "0"
    }

    private fun showResult(tvExpression: TextView, tvResult: TextView) {
        var expression = tvExpression.text.toString()
        expression = expression.replace("%", "*[%]")
        val e = Expression(expression)
        println(expression)
        println(e.calculate().toString())
        val result = e.calculate().toString()

        // saving history
        var hist = History(expression_calc = expression, result_calc = result)
        this.historico.add(hist)
        //---------------------

        if (result == "NaN") {
            tvResult.text = ""
            val text = "Operação inválida"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()

            println("Operação inválida")
        } else {
            tvResult.text = result
        }
    }
}


