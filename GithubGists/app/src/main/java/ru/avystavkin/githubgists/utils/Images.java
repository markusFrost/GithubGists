package ru.avystavkin.githubgists.utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import ru.avystavkin.githubgists.content.Gist;

public final class Images {

    private Images() {
    }

    public static void loadGistAvatar(@NonNull ImageView imageView, @NonNull Gist gist) {
        if (gist == null || gist.getUser() == null)
            return;
        String url = gist.getUser().getAvatarUrl();
        if (!TextUtils.isEmpty(url))
            loadImage(imageView, url);
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
