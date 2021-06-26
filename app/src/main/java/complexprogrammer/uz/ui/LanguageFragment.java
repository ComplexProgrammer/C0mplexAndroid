package complexprogrammer.uz.ui;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Locale;

import complexprogrammer.uz.R;


public class LanguageFragment extends Fragment {

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

        spinner = view.findViewById(R.id.spinner);
        SelectLanguageFragment selectLanguageFragment=new SelectLanguageFragment();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedLang = adapterView.getItemAtPosition(i).toString();
                if(selectedLang.equals("Uzbek")){
                    setLocal(LanguageFragment.this,"uz");
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment, selectLanguageFragment);
                    fragmentTransaction.commit();
                }
                else if(selectedLang.equals("English")){
                    setLocal(LanguageFragment.this,"en");
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment, selectLanguageFragment);
                    fragmentTransaction.commit();
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
    public  void setLocal(Fragment fragment, String langCode){
        Locale locale = new Locale(langCode);
        locale.setDefault(locale);

        Resources resources = fragment.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config,resources.getDisplayMetrics());
    }
}