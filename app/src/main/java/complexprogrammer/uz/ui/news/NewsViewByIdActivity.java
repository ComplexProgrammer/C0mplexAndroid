package complexprogrammer.uz.ui.news;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import complexprogrammer.uz.R;
import complexprogrammer.uz.services.GlideApp;

public class NewsViewByIdActivity extends AppCompatActivity {

    NewsResponse newsResponse;
    ImageView imageView;
    TextView short_title,long_title,text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view_by_id);
        Intent intent=getIntent();
        imageView=findViewById(R.id.imageView);
        short_title=findViewById(R.id.short_title);
        long_title=findViewById(R.id.long_title);
        text=findViewById(R.id.text);
        if(intent.getExtras()!=null){
            newsResponse= (NewsResponse) intent.getSerializableExtra("data");

            short_title.setText(newsResponse.getShort_title_uz());
            long_title.setText(newsResponse.getLong_title_uz());
            text.setText(Html.fromHtml(newsResponse.getText_uz()));
            GlideApp.with(this).load(newsResponse.getImage_url()).into(imageView);
        }
    }
}