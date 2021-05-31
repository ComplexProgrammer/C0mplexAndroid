package complexprogrammer.uz.ui.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import complexprogrammer.uz.R;
import complexprogrammer.uz.services.GlideApp;

public class NewsViewByIdFragment extends Fragment {

    NewsResponse newsResponse;
    ImageView imageView;
    TextView short_title,long_title,text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_view_by_id, container, false);
        Intent intent=getActivity().getIntent();
        imageView=view.findViewById(R.id.imageView);
        short_title=view.findViewById(R.id.short_title);
        long_title=view.findViewById(R.id.long_title);
        text=view.findViewById(R.id.text);
        if(intent.getExtras()!=null){
            newsResponse= (NewsResponse) intent.getSerializableExtra("data");

            short_title.setText(newsResponse.getShort_title_uz());
            long_title.setText(newsResponse.getLong_title_uz());
            text.setText(newsResponse.getText_uz());
            GlideApp.with(this).load(newsResponse.getImage_url()).into(imageView);
        }

        return view;
    }
}