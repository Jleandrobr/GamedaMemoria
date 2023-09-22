package com.example.gamedamemoria

import android.os.Bundle
import android.view.View
import android.widget.GridView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var gridView: GridView
    private lateinit var progressBar : ProgressBar
    private val cards = mutableListOf<Card>()
    private lateinit var Card: Card
    private lateinit var cardAdapter: CardAdapter
    private var primeiroCard: Card? = null
    private var segundoCard: Card? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        this.progressBar = findViewById(R.id.progressBar)
//        progress() // progress bar generico

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
            cards.add(Card)
            cards.shuffle()
        }



        gridView.setOnItemClickListener { _, view, position, _ ->
            val card = cards[position]
            if (!card.capa) {
                Card.revelarCard(card, view)
                if (primeiroCard == null) {
                    primeiroCard = card
                } else {
                    segundoCard = card
                    verificarAcerto()
                }
            }
        }







    }

    private fun verificarAcerto() {
        if (primeiroCard?.id == segundoCard?.id) {
            primeiroCard = null
            segundoCard = null
        } else {
            val handler = android.os.Handler()
            handler.postDelayed({
                Card.EsconderCards()
            }, 1000)
        }
    }


    /// função da barra de progresso
//    private fun progress(){
//        Thread{
//            while (this.progressBar.progress < 100){
//                this.progressBar.progress += 1
//                Thread.sleep(100)
//            }
//            this.progressBar.visibility = View.INVISIBLE
//        }.start()
//    }
}