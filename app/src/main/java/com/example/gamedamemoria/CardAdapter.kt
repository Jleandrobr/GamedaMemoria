package com.example.gamedamemoria

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class CardAdapter(private val context: Context, private val cards: List<Card>) : BaseAdapter() {
    override fun getCount(): Int = cards.size

    override fun getItem(position: Int): Any = cards[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val card = cards[position]
        val view = LayoutInflater.from(context).inflate(R.layout.card_item, null)
        val cardImageView = view.findViewById<ImageView>(R.id.cardImageView)

        if (card.capa) {
            cardImageView.setImageResource(card.id)
        } else {
            cardImageView.setImageResource(R.drawable.card)
        }

        return view
    }
}
