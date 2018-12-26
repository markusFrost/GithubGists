package ru.avystavkin.githubgists.screen.interfaces;

import android.support.annotation.NonNull;

public interface OnItemClickListener<T> {

    void onItemClick(@NonNull T item);

}
