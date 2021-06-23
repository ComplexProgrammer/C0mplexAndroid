package complexprogrammer.uz.ui.account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import complexprogrammer.uz.MainActivity;
import complexprogrammer.uz.R;
import complexprogrammer.uz.models.TextValue;
import complexprogrammer.uz.services.ApiClient;
import complexprogrammer.uz.services.Validation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginTabFragment extends Fragment {

    private UserResponse userResponse;
    EditText email;
    TextInputLayout pass;
    TextInputEditText password;
    TextView forgetPass,email_error,pass_error,alert;
    Button login;
    float v=0;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public Integer getUserId(Context c){
        SharedPreferences pref = c.getSharedPreferences("C0mplexPref", Context.MODE_PRIVATE); // 0 - for private mode
        return Integer.parseInt(pref.getString("user_id","0"));
    }
    public String getUserName(Context c){
        SharedPreferences pref = c.getSharedPreferences("C0mplexPref", Context.MODE_PRIVATE); // 0 - for private mode
        return pref.getString("user_name",null);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container,false);

        alert=view.findViewById(R.id.alert);
        email=view.findViewById(R.id.email);
        email_error=view.findViewById(R.id.email_error);
        pass=view.findViewById(R.id.pass);
        password=view.findViewById(R.id.password);
        pass_error=view.findViewById(R.id.pass_error);
        forgetPass=view.findViewById(R.id.forget_pass);
        login=view.findViewById(R.id.login);


        email.setTranslationX(800);
        pass.setTranslationX(800);
//        password.setTranslationX(800);
        forgetPass.setTranslationX(800);
        login.setTranslationX(800);

        email.setAlpha(v);
        pass.setAlpha(v);
//        password.setAlpha(v);
        forgetPass.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
//        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean email_valid=false,pass_valid=false;
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
                if(email_valid&&pass_valid){
                    LoginViewModel loginViewModel = new LoginViewModel();
                    loginViewModel.email = email.getText().toString();
                    loginViewModel.password = password.getText().toString();
                    Call<TextValue> result = ApiClient.getInterface().Login(loginViewModel);
                    result.enqueue(new Callback<TextValue>() {
                        @Override
                        public void onResponse(Call<TextValue> call, Response<TextValue> response) {
                            if (response.isSuccessful()) {
                                try{
                                    Integer data=Integer.parseInt(response.body().value);
                                    Log.e("data",data.toString());
                                    //data = 0  -> xatolik
                                    //data = -1 -> login yoki parol noto'g'ri
                                    if(data==0){
                                        alert.setText("Serverda xatoli yuz berdi");
                                    }
                                    else
                                    {
                                        if(data==-1){
                                            alert.setText("Login yoki parol noto'g'ri kiritildi");
                                        }
                                        else{
                                            getUserById(data);
                                        }
                                    }
                                }
                                catch(NumberFormatException ex) {
                                    alert.setText(response.body().value);

                                }
                            } else {
                                String message = "Xatolik yuz berdi. keyinroq yana urinib ko'rig";
                                alert.setText(message);
                            }
                        }
                        @Override
                        public void onFailure(Call<TextValue> call, Throwable t) {
                            Log.e("onFailure", call.toString());

                            String message = t.getLocalizedMessage();
                            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
        return view;
    }

    public void getUserById(Integer user_id){

        Log.e("user_id",user_id.toString());
        Call<UserResponse> user= ApiClient.getInterface().GetUserById(user_id);
        user.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    userResponse=response.body();
                    SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("C0mplexPref", Context.MODE_PRIVATE); // 0 - for private mode
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("user_id", user_id.toString());
                    editor.putString("user_name", userResponse.getFirst_name()+" "+userResponse.getLast_name());
                    editor.commit();
                    startActivity(new Intent(getActivity(), MainActivity.class));
//                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction.replace(R.id.nav_host_fragment, new MyAccountFragment(user_id));
//                    fragmentTransaction.commit();
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        adapter = new LoginAdapter(getChildFragmentManager(),getContext(),tabLayout);
//        viewPager = view.findViewById(R.id.pager);
//        viewPager.setAdapter(adapter);

    }

}
