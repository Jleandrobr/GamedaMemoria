package com.example.gamedamemoria

import android.view.View

class Card() {
    private var primeiroCard: Card? = null
    private var segundoCard: Card? = null
    var id: Int = 0
    var capa: Boolean = true
    private lateinit var cardAdapter: CardAdapter

    fun revelarCard(card: Card, view: View) {
        card.capa = true
        cardAdapter.notifyDataSetChanged()
        view.isClickable = false
    }

//    fun verificarAcerto() {
//        if (primeiroCard?.id == segundoCard?.id) {
//            primeiroCard = null
//            segundoCard = null
//        } else {
//            val handler = android.os.Handler()
//            handler.postDelayed({
//                EsconderCards()
//            }, 1000)
//        }
//    }

    fun EsconderCards() {
        primeiroCard?.capa = false
        segundoCard?.capa = false
        cardAdapter.notifyDataSetChanged()
        primeiroCard = null
        segundoCard = null
    }




}