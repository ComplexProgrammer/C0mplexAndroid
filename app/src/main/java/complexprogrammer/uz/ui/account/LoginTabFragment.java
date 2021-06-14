package complexprogrammer.uz.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import complexprogrammer.uz.R;

public class LoginTabFragment extends Fragment {
    EditText email,pass;
    TextView forgetPass;
    Button login;
    float v=0;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container,false);
//        email=view.findViewById(R.id.email);
//        pass=view.findViewById(R.id.pass);
//        forgetPass=view.findViewById(R.id.forget_pass);
//        login=view.findViewById(R.id.login);
//
//        email.setTranslationX(800);
//        pass.setTranslationX(800);
//        forgetPass.setTranslationX(800);
//        login.setTranslationX(800);
//
//        email.setAlpha(v);
//        pass.setAlpha(v);
//        forgetPass.setAlpha(v);
//        login.setAlpha(v);
//
//        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
//        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
//        forgetPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
//        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return view;
    }
}
