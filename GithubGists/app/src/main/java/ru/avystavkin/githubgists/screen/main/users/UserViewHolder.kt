package ru.avystavkin.githubgists.screen.main.users

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import ru.avystavkin.githubgists.R
import ru.avystavkin.githubgists.models.local.User
import ru.avystavkin.githubgists.screen.base.holders.BaseViewHolder
import ru.avystavkin.githubgists.utils.Images

class UserViewHolder(itemView: View) : BaseViewHolder(itemView) {
    @BindView(R.id.user_name)
    protected lateinit var mUserName: TextView

    @BindView(R.id.user_img)
    protected lateinit var mImageView: ImageView

    fun bind(user: User) {
        if (!user.name.isNullOrEmpty()) {
            mUserName.text = String.format("%s ( %d )", user.name!!, user.gistsCount)
        }

        if (!user.url!!.isNullOrEmpty()) {
            Images.loadImage(mImageView, user.url!!)
        }
    }
}

