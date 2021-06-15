package complexprogrammer.uz.ui.account;

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
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import complexprogrammer.uz.R;
import complexprogrammer.uz.models.TextValue;
import complexprogrammer.uz.services.ApiClient;
import complexprogrammer.uz.ui.news.NewsFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginTabFragment extends Fragment {
    LoginAdapter adapter;
    ViewPager viewPager;
    TabLayout tabLayout;

    EditText email,pass;
    TextView forgetPass;
    Button login;
    float v=0;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container,false);

        email=view.findViewById(R.id.email);
        pass=view.findViewById(R.id.pass);
        forgetPass=view.findViewById(R.id.forget_pass);
        login=view.findViewById(R.id.login);

        email.setTranslationX(800);
        pass.setTranslationX(800);
        forgetPass.setTranslationX(800);
        login.setTranslationX(800);

        email.setAlpha(v);
        pass.setAlpha(v);
        forgetPass.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginViewModel loginViewModel = new LoginViewModel();
                loginViewModel.email = email.getText().toString();
                loginViewModel.password = pass.getText().toString();
                Call<TextValue> result = ApiClient.getInterface().Login(loginViewModel);
                result.enqueue(new Callback<TextValue>() {
                    @Override
                    public void onResponse(Call<TextValue> call, Response<TextValue> response) {
                        if (response.isSuccessful()) {
                            String message = "Yulanmoqda...";
                            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                            Toast.makeText(getContext(), response.body().value, Toast.LENGTH_LONG).show();


                        } else {
                            String message = "Xatolik yuz berdi. keyinroq yana urinib ko'rig";
                            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
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
        });
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        adapter = new LoginAdapter(getChildFragmentManager(),getContext(),tabLayout);
//        viewPager = view.findViewById(R.id.pager);
//        viewPager.setAdapter(adapter);

    }

}
