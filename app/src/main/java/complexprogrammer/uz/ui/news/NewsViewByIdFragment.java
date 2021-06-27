package complexprogrammer.uz.ui.news;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import complexprogrammer.uz.R;
import complexprogrammer.uz.services.GlideApp;
import complexprogrammer.uz.ui.LanguageFragment;

public class NewsViewByIdFragment extends Fragment {

    static NewsResponse newsResponse;
    ImageView imageView;
    TextView short_title,long_title,text;
    public static NewsViewByIdFragment newInstance(NewsResponse data) {
        NewsViewByIdFragment fragment = new NewsViewByIdFragment();
        newsResponse=data;
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_view_by_id, container, false);
        imageView=view.findViewById(R.id.imageView);
        short_title=view.findViewById(R.id.short_title);
        long_title=view.findViewById(R.id.long_title);
        text=view.findViewById(R.id.text);
        LanguageFragment languageFragment=new LanguageFragment();
        String langCode=languageFragment.getLangCode(getContext());
        if(langCode.equals("uz")){
            short_title.setText(newsResponse.getShort_title_uz());
            long_title.setText(newsResponse.getLong_title_uz());
            text.setText(Html.fromHtml(newsResponse.getText_uz()));
        } else{
            if(langCode.equals("en")){
                short_title.setText(newsResponse.getShort_title_en());
                long_title.setText(newsResponse.getLong_title_en());
                text.setText(Html.fromHtml(newsResponse.getText_en()));
            }
        }
        GlideApp.with(this).load(newsResponse.getImage_url()).into(imageView);

        return view;
    }
}