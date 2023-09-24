package com.example.gamedamemoria

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridView
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var jogo: Jogo
    private lateinit var progressBar: ProgressBar
    private lateinit var listImage: MutableList<MutableList<ImageView>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.jogo = Jogo()
        this.listImage = mutableListOf()

//        this.progressBar = findViewById(R.id.progressBar)
//        progress() // progress bar generico


        var it = this.jogo.cardIds.iterator()


        for(i in 0..3){
            val linha = mutableListOf<ImageView>()
            for(j in 0..3){
                val id = resources.getIdentifier("image${i}${j}", "id", packageName)
                val imagem = findViewById<ImageView>(id)
                imagem.setImageResource(it.next())
                linha.add(imagem)
            }
            this.listImage.add(linha)
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