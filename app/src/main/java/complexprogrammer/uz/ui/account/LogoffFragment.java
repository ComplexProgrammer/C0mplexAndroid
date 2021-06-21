package complexprogrammer.uz.ui.account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.List;

import complexprogrammer.uz.MainActivity;
import complexprogrammer.uz.R;
import complexprogrammer.uz.models.TextValue;
import complexprogrammer.uz.services.ApiClient;
import complexprogrammer.uz.ui.news.NewsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogoffFragment extends Fragment {

Button button;
    private static Integer user_id;
    public LogoffFragment(Integer user_id){
        this.user_id=user_id;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_logoff, container, false);
        button=view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user_id!=0){
                    Call<TextValue> result = ApiClient.getInterface().SignOut(user_id);
                    result.enqueue(new Callback<TextValue>() {
                        @Override
                        public void onResponse(Call<TextValue> call, Response<TextValue> response) {
                            if(response.isSuccessful()){
                                if(response.body()!=null){
                                    String res=response.body().value;
                                    if(res.equals("0")){
                                        Toast.makeText(getContext(), "Serverda xatoli yuz berdi", Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        if(res.equals("-1")){
                                            Toast.makeText(getContext(), response.body().value, Toast.LENGTH_LONG).show();
                                        }
                                        else {
                                            if(res.equals("1")){
                                                SharedPreferences pref = getContext().getSharedPreferences("C0mplexPref", Context.MODE_PRIVATE); // 0 - for private mode
                                                SharedPreferences.Editor editor = pref.edit();
                                                editor.putString("user_id", "0");
                                                editor.putString("user_name", "");
                                                editor.commit();
                                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                                startActivity(intent);
                                            }
                                        }
                                    }
                                }else {
                                    String message = "Xatolik yuz berdi. keyinroq yana urinib ko'rig";
                                    Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                                }
                            }
                            else {
                                String message = "Xatolik yuz berdi. keyinroq yana urinib ko'rig";
                                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
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