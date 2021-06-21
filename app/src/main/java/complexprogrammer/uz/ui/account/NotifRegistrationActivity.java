package complexprogrammer.uz.ui.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import complexprogrammer.uz.R;

public class NotifRegistrationActivity extends AppCompatActivity {

    private TextView user_name;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif_registration);
        user_name=findViewById(R.id.user_name);
        user_name.setText((String) getIntent().getSerializableExtra("data"));
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotifRegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}