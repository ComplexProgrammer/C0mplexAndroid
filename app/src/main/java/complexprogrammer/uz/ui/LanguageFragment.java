package complexprogrammer.uz.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Locale;

import complexprogrammer.uz.MainActivity;
import complexprogrammer.uz.R;


public class LanguageFragment extends Fragment {

    private AdView mAdView;
    private SharedPreferences preferences;
    Spinner spinner;
    public static final String[] languages ={"Language","English","Uzbek"};

    public LanguageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_language, container, false);
        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
        spinner = view.findViewById(R.id.spinner);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedLang = adapterView.getItemAtPosition(i).toString();
                if(selectedLang.equals("Uzbek")){
                    setLocal(LanguageFragment.this.getActivity(),"uz");
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }
                else if(selectedLang.equals("English")){
                    setLocal(LanguageFragment.this.getActivity(),"en");
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }
                else {
                    Toast.makeText(getContext(), "Please select a Language", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }
    public void setLocal(Context context, String langCode){
        Locale locale = new Locale(langCode);
        locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.createConfigurationContext(config);
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        preferences=context.getSharedPreferences("C0mplexLanguage", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("langCode",langCode);
        editor.apply();
    }
    public String getLangCode(Context context){
        preferences=context.getSharedPreferences("C0mplexLanguage", Context.MODE_PRIVATE);
        String langCode=preferences.getString("langCode",null);
        if(langCode==null){
            setLocal(context,"en");
        }
        else {
            if(langCode.equals("uz")){
                return "uz";
            } else{
                if(langCode.equals("en")){
                    return "en";
                }
            }
        }

        return null;
    }
}