package ru.avystavkin.githubgists.screen.gist_detail.holders;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import butterknife.BindView;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.models.local.Gist;
import ru.avystavkin.githubgists.models.local.GistFileInfo;
import ru.avystavkin.githubgists.screen.base.holders.BaseViewHolder;

public class GistDetailContentViewHolder extends BaseViewHolder {
    @BindView(R.id.gist_file_name)
    TextView mFileName;

    @BindView(R.id.gist_code_content)
    TextView mCodeContent;

    public GistDetailContentViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(@NonNull Gist gist, int position) {
        if (position >= gist.getListFiles().size())
            return;

        GistFileInfo file = gist.getListFiles().get(position);
        if (!TextUtils.isEmpty(file.getFileName()))
            mFileName.setText(file.getFileName());

        if (!TextUtils.isEmpty(file.getContent()))
            mCodeContent.setText(file.getContent());
    }
}
