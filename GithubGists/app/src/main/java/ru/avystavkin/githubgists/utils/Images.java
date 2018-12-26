package ru.avystavkin.githubgists.utils;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

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
        Picasso.with(imageView.getContext())
                .load(url)
                .noFade()
                .into(imageView);
    }
}
