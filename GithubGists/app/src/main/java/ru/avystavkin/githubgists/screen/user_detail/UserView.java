package ru.avystavkin.githubgists.screen.user_detail;

import ru.avystavkin.githubgists.content.User;
import ru.avystavkin.githubgists.screen.gist_detail.GistView;

public interface UserView extends GistView {
    public void showUser(User user);
}
