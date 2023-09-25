package com.example.gamedamemoria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class PerdeuActivity : AppCompatActivity() {
    private lateinit var btnReiniciar: Button
    private lateinit var mainActivity: MainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perdeu)

        this.btnReiniciar = this.findViewById(R.id.btnReiniciar)
        btnReiniciar.setOnClickListener(OnClick())

    }
    inner class OnClick: View.OnClickListener{
        override fun onClick(v: View?){
            val intent = Intent()
            setResult(RESULT_OK, intent)
            mainActivity.reiniciar()
            finish()
        }
    }
}