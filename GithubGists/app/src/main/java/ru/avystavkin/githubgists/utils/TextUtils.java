package ru.avystavkin.githubgists.utils;

import androidx.annotation.Nullable;

public final class TextUtils {

    private TextUtils() {
    }

    public static boolean isEmpty(@Nullable CharSequence text) {
        return text == null || text.length() == 0;
    }

}
