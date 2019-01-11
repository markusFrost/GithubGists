package ru.avystavkin.githubgists.screen.holders;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.content.GistFile;
import ru.avystavkin.githubgists.utils.TextUtils;

public class GistDetailContentViewHolder extends BaseViewHolder {
    @BindView(R.id.gist_file_name)
    TextView mFileName;

    @BindView(R.id.gist_code_content)
    TextView mCodeContent;

    public GistDetailContentViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(@NonNull Gist gist, int position) {
        if (gist != null) {
            List<GistFile> list = gist.getGistFiles();
            if (position >= list.size())
                return;

            GistFile file = list.get(position);
            if (!TextUtils.isEmpty(file.getFileName()))
                mFileName.setText(file.getFileName());

            if (!TextUtils.isEmpty(file.getContent()))
                mCodeContent.setText(file.getContent());
        }
    }

    public TextView getFileName() {
        return mFileName;
    }

    public void setFileName(TextView fileName) {
        this.mFileName = fileName;
    }

    public TextView getCodeContent() {
        return mCodeContent;
    }

    public void setCodeContent(TextView codeContent) {
        this.mCodeContent = codeContent;
    }
}
