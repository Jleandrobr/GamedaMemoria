package com.example.gamedamemoria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridView
import android.widget.ProgressBar

class MainActivity : AppCompatActivity() {

    private lateinit var gridView: GridView
    private lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.progressBar = findViewById(R.id.progressBar)

        progress()
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