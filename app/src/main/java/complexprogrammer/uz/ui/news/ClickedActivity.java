package complexprogrammer.uz.ui.news;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import complexprogrammer.uz.R;
import complexprogrammer.uz.services.GlideApp;

public class ClickedActivity extends AppCompatActivity {

    NewsResponse newsResponse;
    ImageView selectedImage;
    TextView selectedTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicked);
        Intent intent=getIntent();


        selectedImage=findViewById(R.id.selectedImage);
        selectedTitle=findViewById(R.id.selectedTitle);
        if(intent.getExtras()!=null){
            newsResponse= (NewsResponse) intent.getSerializableExtra("data");

            selectedTitle.setText(newsResponse.getShort_title_uz());
            GlideApp.with(this).load(newsResponse.getImage_url()).into(selectedImage);
        }
    }
}