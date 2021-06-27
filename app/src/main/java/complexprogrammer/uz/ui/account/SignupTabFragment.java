package complexprogrammer.uz.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import complexprogrammer.uz.R;
import complexprogrammer.uz.models.TextValue;
import complexprogrammer.uz.services.ApiClient;
import complexprogrammer.uz.services.Validation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupTabFragment extends Fragment {
    TextInputLayout pass,pass2;
    TextInputEditText password,password2;
    EditText first_name,last_name, email;
    TextView fn_error,ln_error,email_error,pass_error,pass2_error,alert;
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
        password=view.findViewById(R.id.password);
        pass2=view.findViewById(R.id.pass2);
        password2=view.findViewById(R.id.password2);
        sign_up=view.findViewById(R.id.sign_up);

        alert=view.findViewById(R.id.alert);
        fn_error=view.findViewById(R.id.fn_error);
        ln_error=view.findViewById(R.id.ln_error);
        email_error=view.findViewById(R.id.email_error);
        pass_error=view.findViewById(R.id.pass_error);
        pass2_error=view.findViewById(R.id.pass2_error);


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
                boolean fn_valid=false,ln_valid=false,email_valid=false,pass_valid=false,pass2_valid=false;
                if(first_name.getText().toString().isEmpty()){
                    fn_valid=false;
                    fn_error.setText("Ism kiritilmadi");
                }
                else {
                    fn_valid=true;
                    fn_error.setText("");
                }
                if(last_name.getText().toString().isEmpty()){
                    ln_valid=false;
                    ln_error.setText("Familya kiritilmadi");
                }
                else {
                    ln_valid=true;
                    ln_error.setText("");
                }
                if(email.getText().toString().isEmpty()){
                    email_valid=false;
                    email_error.setText("Elektron pochta manzili kiritilmadi");
                }
                else {
                    if(Validation.isValidEmail(email.getText())){
                        email_valid=true;
                        email_error.setText("");

                    }
                    else {
                        email_valid=false;
                        email_error.setText("Elektron pochta manzili noto‘g‘ri");
                    }
                }
                if(password.getText().toString().isEmpty()){
                    pass_valid=false;
                    pass_error.setText("Parol kiritilmadi");
                }
                else {
                    pass_valid=true;
                    pass_error.setText("");
                }
                if(password2.getText().toString().isEmpty()){
                    pass2_valid=false;
                    pass2_error.setText("Takroriy parol kiritilmadi");
                }
                else {
                    pass2_valid=true;
                    pass2_error.setText("");
                }
                if(pass_valid&&pass2_valid){
                    if(password.getText().toString().equals(password2.getText().toString())){
                        pass_valid=true;
                        pass2_valid=true;
                        pass_error.setText("");
                        pass2_error.setText("");
                    }
                    else {
                        pass_valid=false;
                        pass2_valid=false;
                        pass_error.setText("parollar mos kelmadi");
                        pass2_error.setText("parollar mos kelmadi");
                    }
                }
                if(fn_valid&&ln_valid&&email_valid&&pass_valid&&pass2_valid){
                    SignUpViewModel model=new SignUpViewModel();
                    model.first_name=first_name.getText().toString();
                    model.last_name=last_name.getText().toString();
                    model.email=email.getText().toString();
                    model.password=password.getText().toString();
                    Call<TextValue> result = ApiClient.getInterface().Register(model);
                    result.enqueue(new Callback<TextValue>() {
                        @Override
                        public void onResponse(Call<TextValue> call, Response<TextValue> response) {
                            if (response.isSuccessful()) {
                                if(response.body().value.equals("0")){
                                    alert.setText("Serverda xatoli yuz berdi");
                                    Toast.makeText(getContext(),"Serverda xatoli yuz berdi" , Toast.LENGTH_LONG).show();
                                }else
                                {
                                    if(response.body().value.equals("1")){
                                        Intent intent = new Intent(getActivity(), NotifRegistrationActivity.class).putExtra("data",model.first_name+" "+model.last_name);
                                        startActivity(intent);
                                    }else {
                                        alert.setText(response.body().value);
                                    }
                                }


                            } else {
                                String message = "Xatolik yuz berdi. keyinroq yana urinib ko'rig";
                                alert.setText(message);
                            }
                        }

                        @Override
                        public void onFailure(Call<TextValue> call, Throwable t) {

                        }
                    });
                }

            }
        });


        return view;
    }
}
