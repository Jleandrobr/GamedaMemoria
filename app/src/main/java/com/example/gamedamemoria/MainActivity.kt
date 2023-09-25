package com.example.gamedamemoria

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.Collections

class MainActivity : AppCompatActivity() {

    private lateinit var jogo: Jogo
    private lateinit var progressBar: ProgressBar
    private lateinit var btnRecomecar: Button
    private var virada: Boolean = false
    private var primeiroCard: ImageView? = null
    private var segundoCard: ImageView? = null
    private lateinit var listImage: MutableList<MutableList<ImageView>>
//    val cards = mutableListOf<Int>()


//    val cardIds: MutableList<Int> = mutableListOf(
//        R.drawable.gustavo, R.drawable.gustavo,
//        R.drawable.luiz, R.drawable.luiz,
//        R.drawable.leonidas, R.drawable.leonidas,
//        R.drawable.lafayette, R.drawable.lafayette,
//        R.drawable.valeria, R.drawable.valeria,
//        R.drawable.candido, R.drawable.candido,
//        R.drawable.fred, R.drawable.fred,
//        R.drawable.alex, R.drawable.alex
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val cardIds = listOf(
//            R.drawable.gustavo, R.drawable.gustavo,
//            R.drawable.luiz, R.drawable.luiz,
//            R.drawable.leonidas, R.drawable.leonidas,
//            R.drawable.lafayette, R.drawable.lafayette,
//            R.drawable.valeria, R.drawable.valeria,
//            R.drawable.candido, R.drawable.candido,
//            R.drawable.fred, R.drawable.fred,
//            R.drawable.alex, R.drawable.alex,
//        )

        // Lista de IDs das imagens correspondentes
//        val idsDasImagens = mutableListOf<Int>()
//
//// Obtém os IDs das imagens a partir dos nomes
//        for (id in cardIds) {
//            val imagemId = resources.getIdentifier(id.toString(), "drawable", packageName)
//            idsDasImagens.add(imagemId)
//        }


        btnRecomecar = findViewById(R.id.btnRecomecar)
        progressBar = findViewById(R.id.progressBar)
        jogo = Jogo()
        listImage = mutableListOf()


        val it = this.jogo.cardIds.iterator()

        for (i in 0..3) {
            val linha = mutableListOf<ImageView>()
            for (j in 0..3) {
                val id = resources.getIdentifier("image${i}${j}", "id", packageName)
                val imagem = findViewById<ImageView>(id)
                val imagemId = it.next()
                val index = i * 4 + j // Calcula o índice correspondente na lista cardIds
//                imagem.setImageResource(cardIds[index])
                // Armazene o índice na ImageView como uma tag
                imagem.tag = index
                imagem.setImageResource(imagemId)
                imagem.tag = imagemId
//                imagem.setImageResource(it.next())
                imagem.setImageResource(R.drawable.card)
                imagem.setOnClickListener { OnClickItem().onItemClick(null, imagem, index, 0) }
                linha.add(imagem)
            }
            listImage.add(linha)
        }
    }

    inner class OnClickItem : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val card = view as? ImageView

            if (card != null) {
                if (!virada) {
                    virada = true
                    primeiroCard = card
                    primeiroCard?.setImageResource(jogo.getImageId(position))
                } else {
                    virada = false
                    segundoCard = card
                    segundoCard?.setImageResource(jogo.getImageId(position))

                    if (primeiroCard != null && segundoCard != null) {
                        val card1IndexLinha = listImage.indexOfFirst { it.contains(primeiroCard) }
                        val card2IndexLinha = listImage.indexOfFirst { it.contains(segundoCard) }
                        if (card1IndexLinha != -1 && card2IndexLinha != -1) {
                            val card1Column = listImage[card1IndexLinha].indexOf(primeiroCard)
                            val card2Column = listImage[card2IndexLinha].indexOf(segundoCard)

                            if (card1IndexLinha != -1 && card2IndexLinha != -1 && card1Column != -1 && card2Column != -1) {
                                if (jogo.checkMatch(card1IndexLinha, card2IndexLinha, card1Column, card2Column)) {
                                    // Cards correspondentes, mantenha-os virados.
                                    primeiroCard = null
                                    segundoCard = null
                                } else {
                                    // Cartões não correspondentes, aguarde um momento e depois vire-os para trás.
                                    Handler().postDelayed({
                                        primeiroCard?.setImageResource(R.drawable.card)
                                        segundoCard?.setImageResource(R.drawable.card)
                                        primeiroCard = null
                                        segundoCard = null
                                    }, 1000)
                                }
                            }
                        }

                    }
                }
            }

        }
    }
}
//    private fun onCardClick(card: ImageView) {
//        if (!virada) {
//            virada = true
//            primeiroCard = card
//            primeiroCard?.setImageResource(jogo.getImageId(card.id))
//        } else {
//            virada = false
//            segundoCard = card
//            segundoCard?.setImageResource(jogo.getImageId(card.id))
//
//            if (primeiroCard != null && segundoCard != null) {
//                val card1Index = listImage.indexOfFirst { it.contains(primeiroCard) }
//                val card2Index = listImage.indexOfFirst { it.contains(segundoCard) }
//
//                if (card1Index != -1 && card2Index != -1) {
//                    if (jogo.checkMatch(card1Index, card2Index)) {
//                        // Cartões correspondentes, mantenha-os virados.
//                        primeiroCard = null
//                        segundoCard = null
//                    } else {
//                        // Cartões não correspondentes, aguarde um momento e depois vire-os para trás.
//                        Handler().postDelayed({
//                            primeiroCard?.setImageResource(R.drawable.card)
//                            segundoCard?.setImageResource(R.drawable.card)
//                            primeiroCard = null
//                            segundoCard = null
//                        }, 1000)
//                    }
//                }
//            }
//
//        }
//    }
//}

//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import android.widget.AdapterView
//import android.widget.Button
//import android.widget.ImageView
//import android.widget.ProgressBar
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.isInvisible
//import kotlin.random.Random
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var jogo: Jogo
//    private lateinit var progressBar: ProgressBar
//    private lateinit var btnRecomecar: Button
//    private var virada: Boolean = true
//    var primeiroCard: ImageView? = null
//    var segundoCard: ImageView? = null
//    private lateinit var listImage: MutableList<MutableList<ImageView>>
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        this.btnRecomecar = findViewById(R.id.btnRecomecar)
//        this.progressBar = findViewById(R.id.progressBar)
//        this.jogo = Jogo()
//        this.listImage = mutableListOf()
//
//        val onClickItem = OnClickItem()
//
//        for (i in 0..3) {
//            val linha = mutableListOf<ImageView>()
//            for (j in 0..3) {
//                val id = resources.getIdentifier("image${i}${j}", "id", packageName)
//                val imagem = findViewById<ImageView>(id)
//                imagem.setImageResource(R.drawable.card)
//                imagem.setOnClickListener { onClickItem.onItemClick(null, imagem, id, 0) }
//                linha.add(imagem)
//            }
//            this.listImage.add(linha)
//        }
//    }
//
//    inner class OnClickItem : AdapterView.OnItemClickListener {
//        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//            val i = p2 / 10
//            val j = p2 % 10
//
//            if (!jogo.foiCombinado(i, j)) {
//                Log.i("APP_JOGO", "verificou")
//                if (!virada) {
//                    primeiroCard = listImage[i][j]
//                    primeiroCard?.setImageResource(jogo.cardIds[i][j])
//                } else {
//                    segundoCard = listImage[i][j]
//                    segundoCard?.setImageResource(jogo.cardIds[i][j])
//                }
//                virada = !virada
//
//                if (virada) {
//                    if (jogo.verificarJogada(primeiroCard, segundoCard)) {
//                        primeiroCard = null
//                        segundoCard = null
//                    }
//                }
//            }
//        }
//    }
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import android.widget.AdapterView
//import android.widget.AdapterView.OnItemClickListener
//import android.widget.Button
//import android.widget.GridView
//import android.widget.ImageView
//import android.widget.ProgressBar
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.isInvisible
//
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var jogo: Jogo
//    private lateinit var progressBar: ProgressBar
//    private lateinit var btnRecomecar: Button
//    var virada: Boolean = false
//    private var primeiroCard: ImageView? = null
//    private var segundoCard: ImageView? = null
//    private lateinit var listImage: MutableList<MutableList<ImageView>>
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        this.btnRecomecar = findViewById(R.id.btnRecomecar)
//        this.jogo = Jogo()
//        this.listImage = mutableListOf()
//
//
////        this.progressBar = findViewById(R.id.progressBar)
////        progress() // progress bar generico
//
//
//        var it = this.jogo.cardIds.iterator()
//
//        for(i in 0..3){
//            val linha = mutableListOf<ImageView>()
//            for(j in 0..3){
//                val id = resources.getIdentifier("image${i}${j}", "id", packageName)
//                val imagem = findViewById<ImageView>(id)
//                imagem.setImageResource(it.next())
//                linha.add(imagem)
//            }
//            this.listImage.add(linha)
//        }
//
////        for(i in 0..3) {
////            val linha = mutableListOf<ImageView>()
////            for (j in 0..3) {
//////                val card = findViewById<ImageView>(R.drawable.card)
////                val id = resources.getIdentifier("${card}", "drawable", packageName)
////                val imagemCapa = findViewById<ImageView>(R.id.card)
////
////                imagemCapa.setImageResource(it.next())
////                linha.add(imagemCapa)
////            }
////            this.listImage.add(linha)
////        }
//
//
//
//
//
//    }
//    inner class OnClickItem: OnItemClickListener{
//        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//            if (p1?.id == p2){
//                primeiroCard?.setImageResource(R.drawable.card)
//                segundoCard?.setImageResource(R.drawable.card)
//            }
//            else{
//                primeiroCard?.isInvisible
//            }
//
//        }
//    }


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
//}