package ru.avystavkin.githubgists.utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;

public final class Images {

    private Images() {
    }

    public static void loadImage(@NonNull ImageView imageView, @NonNull String url) {
        Picasso.get()
                .load(url)
                .resize(45, 45)
                .noFade()
                .centerCrop()
                .into(imageView);
    }
}
