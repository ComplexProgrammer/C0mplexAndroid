package complexprogrammer.uz.ui.home;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import complexprogrammer.uz.R;
import complexprogrammer.uz.services.GlideApp;
import complexprogrammer.uz.ui.LanguageFragment;


public class HomeViewByIdFragment extends Fragment {



    public static HomeViewModel homeViewModel;
    ImageView imageView;
    TextView title,text;
    private AdView mAdView;

    public static HomeViewByIdFragment newInstance(HomeViewModel data) {
        HomeViewByIdFragment fragment = new HomeViewByIdFragment();
        homeViewModel=data;
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_view_by_id, container, false);
//        mAdView = view.findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder()
//                .build();
//        mAdView.loadAd(adRequest);
        imageView=view.findViewById(R.id.imageView);
        title=view.findViewById(R.id.title);
        text=view.findViewById(R.id.text);
        LanguageFragment languageFragment=new LanguageFragment();
        String langCode=languageFragment.getLangCode(getContext());
        if(langCode.equals("uz")){
            title.setText(homeViewModel.getTitle_uz());
            text.setText(Html.fromHtml(homeViewModel.getText_uz()));
        } else{
            if(langCode.equals("en")){
                title.setText(homeViewModel.getTitle_en());
                text.setText(Html.fromHtml(homeViewModel.getText_en()));
            }
        }
        GlideApp.with(this).load(homeViewModel.getImage_url()).into(imageView);
        return view;
    }
    public void onBackPressed() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}