package br.com.maypay.calculadora

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_histo.*

class HistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_histo)

        // receiving a simple string
       val title = intent.getStringExtra("Title")
       textView.text = title + "\n"

        // receiving a single historic
        val historic = intent.getParcelableExtra<History>("Historic")
        textView.text = textView.text.toString() +  "expression: "+historic?.expression_calc + " result: "+historic?.result_calc + "\n"

        // receiving an array of historics
        var historico = intent.getParcelableArrayListExtra<History>("Historics")
        if (historico != null){
            for (i in historico.indices){
                textView.text =  textView.text.toString() + "expression: "+historico[i].expression_calc + " result: "+historico[i].result_calc + "\n"
                println(historico[i].expression_calc + " " + historico[i].result_calc)
            }
        }

    }
}