package com.example.gamedamemoria

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


}