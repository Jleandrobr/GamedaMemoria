package com.example.gamedamemoria

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.util.Collections

class MainActivity : AppCompatActivity() {

    private lateinit var jogo: Jogo
    private lateinit var progressBar: ProgressBar
    private lateinit var btnRecomecar: Button
    private var virada: Boolean = false
    private var primeiroCard: ImageView? = null
    private var segundoCard: ImageView? = null
    private var barraThread: Thread? = null
    private var pontos: Int = 0
    private lateinit var listImage: MutableList<MutableList<ImageView>>


    val perdeuResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK){
        }
    }

    val ganhouResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK){

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnRecomecar = findViewById(R.id.btnRecomecar)
        btnRecomecar.setOnClickListener{reiniciar()}
        progressBar = findViewById(R.id.progressBar)
        jogo = Jogo()
        listImage = mutableListOf()


        progress()
        val it = this.jogo.cardIds.iterator()

        for (i in 0..3) {
            val linha = mutableListOf<ImageView>()
            for (j in 0..3) {
                val id = resources.getIdentifier("image${i}${j}", "id", packageName)
                val imagem = findViewById<ImageView>(id)
                val imagemId = it.next()
                val index = i * 4 + j // calcula o indice correspondente na lista cardIds
                // armazena o indice na ImageView como uma tag
                imagem.tag = index
                imagem.setImageResource(imagemId)
                imagem.setImageResource(R.drawable.card)
                imagem.setOnClickListener { OnClickItem().onItemClick(null, imagem, index, 0) }
                linha.add(imagem)
            }
            listImage.add(linha)
        }
    }
    fun progress(){
        Thread{
            while (this.progressBar.progress < 100){
                this.progressBar.progress += 1
                Thread.sleep(500)
            }
            if(this.progressBar.progress == 100){
                progressBar.progress = 0
                perdeuResult.launch(Intent(this, PerdeuActivity::class.java))
            }

        }.start()
    }

    fun somarPontos(pontos: Int){
        if(pontos >= 8){
            ganhouResult.launch(Intent(this, GanhouActivity::class.java))
            progressBar.progress = 0
        }
    }

    fun reiniciar() {
        progressBar.progress = 0
        pontos = 0
        virada = false
        primeiroCard = null
        segundoCard = null

        jogo.cardIds.clear()
        jogo.cardIds.addAll(
            listOf(
                R.drawable.gustavo, R.drawable.gustavo,
                R.drawable.luiz, R.drawable.luiz,
                R.drawable.leonidas, R.drawable.leonidas,
                R.drawable.lafayette, R.drawable.lafayette,
                R.drawable.valeria, R.drawable.valeria,
                R.drawable.candido, R.drawable.candido,
                R.drawable.fred, R.drawable.fred,
                R.drawable.alex, R.drawable.alex
            )
        )
        jogo.embaralhar()
        
        val it = this.jogo.cardIds.iterator()

        for (i in 0..3) {
            val linha = mutableListOf<ImageView>()
            for (j in 0..3) {
                val id = resources.getIdentifier("image${i}${j}", "id", packageName)
                val imagem = findViewById<ImageView>(id)
                val imagemId = it.next()
                val index = i * 4 + j // calcula o indice correspondente na lista cardIds
                // armazene o indice na ImageView como uma tag
                imagem.tag = index
                imagem.setImageResource(imagemId)
                imagem.setImageResource(R.drawable.card)
                imagem.setOnClickListener { OnClickItem().onItemClick(null, imagem, index, 0) }
                linha.add(imagem)
            }
            listImage.add(linha)
        }
        progress()
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
                                if (jogo.verificaJogo(card1IndexLinha, card2IndexLinha, card1Column, card2Column)) {
                                    // se cards correspondentes, mantenha-os virados
                                    primeiroCard = null
                                    segundoCard = null
                                    pontos++
                                    somarPontos(pontos)
                                } else {
                                    // se não cards não correspondentes, espere o delayed para virar os cards para baixo
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