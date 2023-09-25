package com.example.gamedamemoria

import android.util.Log
import android.widget.ImageView
import kotlin.random.Random


class Jogo {

    //    val cardIds: MutableList<Int> = mutableListOf()
    var cardIds: MutableList<Int>

    init {
        this.cardIds = mutableListOf(
            R.drawable.gustavo, R.drawable.gustavo,
            R.drawable.luiz, R.drawable.luiz,
            R.drawable.leonidas, R.drawable.leonidas,
            R.drawable.lafayette, R.drawable.lafayette,
            R.drawable.valeria, R.drawable.valeria,
            R.drawable.candido, R.drawable.candido,
            R.drawable.fred, R.drawable.fred,
            R.drawable.alex, R.drawable.alex,
        )
        embaralhar()
    }

    fun embaralhar(): MutableList<Int> {
        this.cardIds.shuffle()
        return cardIds
    }


    fun getImageId(index: Int): Int {
        if (index >= 0 && index < cardIds.size) {
            return cardIds[index]
        }
        return R.drawable.card // retorna o card padrão se o indice estiver fora do intervalo.
    }

    fun checkMatch(
        card1IndexLinha: Int,
        card2IndexLinha: Int,
        card1Column: Int,
        card2Column: Int
    ): Boolean {
        if (card1IndexLinha >= 0 && card2IndexLinha < cardIds.size) {
            val index1 =
                card1IndexLinha * 4 + card1Column // calcula o indice na lista cardIds para o primeiro card
            val index2 =
                card2IndexLinha * 4 + card2Column // calcula o indice na lista cardIds para o segundo card

            if(cardIds[index1] == cardIds[index2]){  //se o index gerado forem iguais, true
                return true
            }
        }
        return false // retorna false se algum dos indices estiver fora do intervalo.
    }
}


//class Jogo {
//
//    var cardIds: MutableList<MutableList<Int>> = mutableListOf()
//    private var cartaVirada: MutableList<MutableList<Boolean>> = mutableListOf()
//    private var primeiroCard: ImageView? = null
//    private var segundoCard: ImageView? = null
//
//    init {
//        val ids = mutableListOf(
//            R.drawable.gustavo, R.drawable.gustavo,
//            R.drawable.luiz, R.drawable.luiz,
//            R.drawable.leonidas, R.drawable.leonidas,
//            R.drawable.lafayette, R.drawable.lafayette,
//            R.drawable.valeria, R.drawable.valeria,
//            R.drawable.candido, R.drawable.candido,
//            R.drawable.fred, R.drawable.fred,
//            R.drawable.alex, R.drawable.alex
//        )
//
//        for (i in 0..3) {
//            val linhaCartas = mutableListOf<Int>()
//            val linhaVirada = mutableListOf<Boolean>()
//            for (j in 0..3) {
//                val index = Random.nextInt(ids.size)
//                linhaCartas.add(ids.removeAt(index))
//                linhaVirada.add(false)
//            }
//            cardIds.add(linhaCartas)
//            cardIds.shuffle()
//            cartaVirada.add(linhaVirada)
//        }
//    }
//
//    fun foiCombinado(i: Int, j: Int): Boolean {
//        Log.i("APP_JOGO", "combinou")
//        return cartaVirada[i][j]
//    }
//
//    fun verificarJogada(primeiro: ImageView?, segundo: ImageView?): Boolean {
//        val i1 = primeiroCard?.id?.div(10) ?: -1
//        val j1 = primeiroCard?.id?.rem(10) ?: -1
//        val i2 = segundoCard?.id?.div(10) ?: -1
//        val j2 = segundoCard?.id?.rem(10) ?: -1
//
//        if (i1 == -1 || j1 == -1 || i2 == -1 || j2 == -1) {
//            return false
//        }
//
//        if (cardIds[i1][j1] == cardIds[i2][j2]) {
//            cartaVirada[i1][j1] = true
//            cartaVirada[i2][j2] = true
//            return true
//        } else {
//            return false
//        }
//    }
//}

//class Jogo {
//
//    var cardIds: MutableList<Int>
//
//    init {
//        this.cardIds = mutableListOf(
//            R.drawable.gustavo, R.drawable.gustavo,
//        R.drawable.luiz, R.drawable.luiz,
//        R.drawable.leonidas, R.drawable.leonidas,
//        R.drawable.lafayette, R.drawable.lafayette,
//        R.drawable.valeria, R.drawable.valeria,
//        R.drawable.candido, R.drawable.candido,
//        R.drawable.fred, R.drawable.fred,
//        R.drawable.alex, R.drawable.alex,
//        )
//        embaralhar()
//    }
//
//    fun embaralhar(): MutableList<Int> {
//        this.cardIds.shuffle()
//        return cardIds
//    }
//
//    fun esconderCard(){
//        cardIds
//
//        return
//    }
//
//
//}