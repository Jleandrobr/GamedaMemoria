package com.example.gamedamemoria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridView
import android.widget.ProgressBar
import java.util.Collections

class MainActivity : AppCompatActivity() {

    private lateinit var gridView: GridView
    private lateinit var progressBar : ProgressBar
    private val cards = mutableListOf<Card>()
    private lateinit var primeiroCard: Card
    private lateinit var segundoCard: Card



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.progressBar = findViewById(R.id.progressBar)
        progress() // progress bar generico

        val cardIds = listOf(
            R.drawable.gustavo, R.drawable.gustavo,
            R.drawable.luiz, R.drawable.luiz,
            R.drawable.leonidas, R.drawable.leonidas,
            R.drawable.lafayette, R.drawable.lafayette,
            R.drawable.valeria, R.drawable.valeria,
            R.drawable.candido, R.drawable.candido,
            R.drawable.fred, R.drawable.fred,
            R.drawable.alex, R.drawable.alex,
        )

        for(id in cardIds) {
            cards.add(Card(id))
            cards.shuffle()
        }





//        gridView.setOnItemClickListener{_, view, position, _ ->
//            val card =
//        }





    }








    /// função da barra de progresso
    private fun progress(){
        Thread{
            while (this.progressBar.progress < 100){
                this.progressBar.progress += 1
                Thread.sleep(100)
            }
            this.progressBar.visibility = View.INVISIBLE
        }.start()
    }
}