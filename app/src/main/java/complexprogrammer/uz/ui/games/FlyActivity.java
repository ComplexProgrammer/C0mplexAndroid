package complexprogrammer.uz.ui.games;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import complexprogrammer.uz.R;

public class FlyActivity extends AppCompatActivity {

    private boolean isMute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fly);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FlyActivity.this,FlyGameActivity.class));
            }
        });
        TextView textView=findViewById(R.id.highScoreTxt);
        SharedPreferences preferences=getSharedPreferences("FlyGame",MODE_PRIVATE);
        textView.setText(getString(R.string.high_score)+preferences.getInt("highscore",0));
        isMute=preferences.getBoolean("isMute",false);
        ImageView imageView=findViewById(R.id.volumeCtrl);
        if(isMute){
            imageView.setImageResource(R.drawable.ic_baseline_volume_off_24);
        }
        else{
            imageView.setImageResource(R.drawable.ic_twotone_volume_up_24);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMute=!isMute;
                if(isMute){
                    imageView.setImageResource(R.drawable.ic_baseline_volume_off_24);
                }
                else{
                    imageView.setImageResource(R.drawable.ic_twotone_volume_up_24);
                }
                SharedPreferences.Editor editor=preferences.edit();
                editor.putBoolean("isMute",isMute);
                editor.apply();
            }
        });
    }
}