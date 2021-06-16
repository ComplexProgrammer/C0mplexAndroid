package complexprogrammer.uz.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import complexprogrammer.uz.R;
import complexprogrammer.uz.models.TextValue;
import complexprogrammer.uz.services.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupTabFragment extends Fragment {
    EditText first_name,last_name, email,pass,pass2;
    Button sign_up;
    float v=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment,container,false);

        first_name=view.findViewById(R.id.first_name);
        last_name=view.findViewById(R.id.last_name);
        email=view.findViewById(R.id.email);
        pass=view.findViewById(R.id.pass);
        pass2=view.findViewById(R.id.pass2);
        sign_up=view.findViewById(R.id.sign_up);

        first_name.setTranslationX(800);
        last_name.setTranslationX(800);
        email.setTranslationX(800);
        pass.setTranslationX(800);
        pass2.setTranslationX(800);
        sign_up.setTranslationX(800);

        first_name.setAlpha(v);
        last_name.setAlpha(v);
        email.setAlpha(v);
        pass.setAlpha(v);
        pass2.setAlpha(v);
        sign_up.setAlpha(v);

        first_name.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(100).start();
        last_name.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        pass2.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();
        sign_up.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1100).start();

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pass.getText()==pass2.getText()){
                    SignUpViewModel model=new SignUpViewModel();
                    model.first_name=first_name.getText().toString();
                    model.last_name=last_name.getText().toString();
                    model.email=email.getText().toString();
                    model.password=pass.getText().toString();
                    Call<TextValue> result = ApiClient.getInterface().Register(model);
                    result.enqueue(new Callback<TextValue>() {
                        @Override
                        public void onResponse(Call<TextValue> call, Response<TextValue> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getContext(), response.body().value, Toast.LENGTH_LONG).show();

                            } else {
                                String message = "Xatolik yuz berdi. keyinroq yana urinib ko'rig";
                                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<TextValue> call, Throwable t) {

                        }
                    });
                }
                else {
                    Toast.makeText(getContext(), "passwords do not match please try again", Toast.LENGTH_LONG).show();
                }

            }
        });


        return view;
    }
}
