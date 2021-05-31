package complexprogrammer.uz.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import complexprogrammer.uz.R;
import complexprogrammer.uz.services.GlideApp;

public class HomeViewByIdActivity extends AppCompatActivity {

    HomeViewModel homeViewModel;
    ImageView imageView;
    TextView title,text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view_by_id);
        Intent intent=getIntent();
        imageView=findViewById(R.id.imageView);
        title=findViewById(R.id.title);
        text=findViewById(R.id.text);
        if(intent.getExtras()!=null){
            homeViewModel= (HomeViewModel) intent.getSerializableExtra("data");
            title.setText(homeViewModel.getTitle());
            text.setText(Html.fromHtml(homeViewModel.getText()));
            GlideApp.with(this).load(homeViewModel.getImage_url()).into(imageView);
        }
    }
}