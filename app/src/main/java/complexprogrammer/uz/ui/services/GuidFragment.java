package complexprogrammer.uz.ui.services;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.UUID;

import complexprogrammer.uz.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GuidFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuidFragment extends Fragment {

    Button GuidCreation,GuidCopy;
    TextView GuidValue;
    private AdView mAdView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GuidFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GuidFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GuidFragment newInstance(String param1, String param2) {
        GuidFragment fragment = new GuidFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guid, container, false);
        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
        GeneratedGuid(view);
        GuidCreation=view.findViewById(R.id.GuidCreation);
        GuidCopy=view.findViewById(R.id.GuidCopy);
        GuidCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneratedGuid(view);
                GuidCopy.setTextColor(getResources().getColor(R.color.White));
                GuidCopy.setBackgroundColor(getResources().getColor(R.color.Green));

                GuidCopy.setText("Copy");
            }
        });
        GuidCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", GuidValue.getText());
                clipboard.setPrimaryClip(clip);
                GuidCopy.setTextColor(getResources().getColor(R.color.Green));
                GuidCopy.setBackgroundColor(getResources().getColor(R.color.White));
                GuidCopy.setText("Copyed!");
            }
        });
        return view;
    }
    private void GeneratedGuid(View view){
        String  uniqueID = UUID.randomUUID().toString();
        GuidValue=view.findViewById(R.id.GuidValue);
        GuidValue.setText(uniqueID);
    }
}