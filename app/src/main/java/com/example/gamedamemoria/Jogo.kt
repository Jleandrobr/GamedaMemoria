package com.example.gamedamemoria

import android.util.Log
import android.widget.ImageView
import kotlin.random.Random


class Jogo {

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
        card1IndexLinha: Int, card2IndexLinha: Int, card1Column: Int, card2Column: Int): Boolean {
        if (card1IndexLinha >= 0 && card2IndexLinha < cardIds.size) {
            val index1 = card1IndexLinha * 4 + card1Column // calcula o indice na lista cardIds para o primeiro card
            val index2 = card2IndexLinha * 4 + card2Column // calcula o indice na lista cardIds para o segundo card

            if(cardIds[index1] == cardIds[index2]){  //se os indexs gerado forem iguais, true
                return true
            }
        }
        return false // retorna false se algum dos indices estiver fora do intervalo.
    }


}
