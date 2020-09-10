package br.com.maypay.calculadora

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jdk.internal.org.objectweb.asm.tree.analysis.Interpreter
import kotlinx.android.synthetic.main.activity_main.*
import javax.script.ScriptEngine

import javax.script.ScriptEngineManager





class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btNumber0.setOnClickListener {
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
            val manager = ScriptEngineManager()
            val engine: ScriptEngine = manager.getEngineByName("js")
            val result: Any = engine.eval("4*5")
        }
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