package ru.avystavkin.githubgists.utils

import android.widget.ImageView

import com.squareup.picasso.Picasso

object Images {

    fun loadImage(imageView: ImageView, url: String) {
        Picasso.get()
                .load(url)
                .resize(45, 45)
                .noFade()
                .centerCrop()
                .into(imageView)
    }
}
