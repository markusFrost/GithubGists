package ru.avystavkin.githubgists.screen.interfaces;

import androidx.annotation.NonNull;

public interface OnItemClickListener<T> {

    void onItemClick(@NonNull T item);

}
