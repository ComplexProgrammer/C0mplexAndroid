package complexprogrammer.uz.ui.account;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.ParseException;

import complexprogrammer.uz.R;
import complexprogrammer.uz.services.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyAccountFragment extends Fragment {
    UserResponse userResponse;
    TextView first_name,last_name,middle_name,role,email,phone_number,login,visit_count,reg_date,change_date,last_access_date,last_release_date;
    private static Integer user_id;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyAccountFragment(Integer user_id) {
        this.user_id=user_id;

        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_my_account, container, false);

        role=view.findViewById(R.id.role);
        first_name=view.findViewById(R.id.first_name);
        last_name=view.findViewById(R.id.last_name);
        middle_name=view.findViewById(R.id.middle_name);
        email=view.findViewById(R.id.email);
        phone_number=view.findViewById(R.id.phone_number);
        login=view.findViewById(R.id.login);
        visit_count=view.findViewById(R.id.visit_count);
        reg_date=view.findViewById(R.id.reg_date);
        change_date=view.findViewById(R.id.change_date);
        last_access_date=view.findViewById(R.id.last_access_date);
        last_release_date=view.findViewById(R.id.last_release_date);
        getUserById(user_id);

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
                    first_name.setText(userResponse.getFirst_name());
                    last_name.setText(userResponse.getLast_name());
                    middle_name.setText(userResponse.getMiddle_name());
                    role.setText(userResponse.getRole());
                    email.setText(userResponse.getEmail());
                    phone_number.setText(userResponse.getPhone_number());
                    login.setText(userResponse.getLogin());
                    visit_count.setText(userResponse.getVisit_count());

                    String dtStart = "2010-10-15T09:27:37";
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    SimpleDateFormat myFormat = new SimpleDateFormat("dd.MM.yyy HH:mm:ss");
                    try {
                        if(userResponse.getReg_date()!=null){
                            reg_date.setText(myFormat.format(format.parse(userResponse.getReg_date())));
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        if(userResponse.getChange_date()!=null){
                            change_date.setText(myFormat.format(format.parse(userResponse.getChange_date())));

                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        if(userResponse.getLast_access_date()!=null){
                            last_access_date.setText(myFormat.format(format.parse(userResponse.getLast_access_date())));

                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        if(userResponse.getLast_release_date()!=null){
                            last_release_date.setText(myFormat.format(format.parse(userResponse.getLast_release_date())));

                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }
}