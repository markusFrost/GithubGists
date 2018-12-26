package ru.avystavkin.githubgists.screen.gists.popular;


import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.User;
import ru.avystavkin.githubgists.screen.gists.holders.UserPopularHolder;
import ru.avystavkin.githubgists.widget.BaseAdapter;


public class UserPopularAdapter extends BaseAdapter<UserPopularHolder, User> {

    public UserPopularAdapter(@NonNull List<User> items) {
        super(items);
    }

    @Override
    public UserPopularHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserPopularHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(UserPopularHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.bind(getItem(position));
    }
}

