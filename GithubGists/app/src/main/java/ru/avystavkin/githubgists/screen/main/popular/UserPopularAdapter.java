package ru.avystavkin.githubgists.screen.main.popular;


import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.User;
import ru.avystavkin.githubgists.screen.holders.UserViewHolder;
import ru.avystavkin.githubgists.widget.BaseAdapter;


public class UserPopularAdapter extends BaseAdapter<UserViewHolder, User> {

    public UserPopularAdapter(@NonNull List<User> items) {
        super(items);
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.bind(getItem(position));
    }
}

