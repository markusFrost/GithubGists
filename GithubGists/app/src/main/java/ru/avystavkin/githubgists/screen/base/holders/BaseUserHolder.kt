package ru.avystavkin.githubgists.screen.base.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import ru.avystavkin.githubgists.R
import ru.avystavkin.githubgists.models.local.User
import ru.avystavkin.githubgists.utils.Images

abstract class BaseUserHolder(itemView: View) : BaseViewHolder(itemView) {

    @BindView(R.id.gist_user_name)
    protected lateinit var mUserName: TextView

    @BindView(R.id.gist_img)
    protected lateinit var mImageView: ImageView

    fun bind(user: User) {
        if (user.name!!.isNotEmpty()) {
            mUserName.text = user.name!!
        }

        if (user.url!!.isNotEmpty()) {
            Images.loadImage(mImageView, user.url!!)
        }
    }
}
