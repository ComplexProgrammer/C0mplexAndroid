package complexprogrammer.uz.ui.news;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import complexprogrammer.uz.NoInternetFragment;
import complexprogrammer.uz.R;
import complexprogrammer.uz.models.NetworkUtil;
import complexprogrammer.uz.services.ApiClient;
import complexprogrammer.uz.services.GlideApp;
import complexprogrammer.uz.ui.LanguageFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsFragment extends Fragment {
    private Button openNewsBtn;
    private List<NewsResponse> newsResponseList=new ArrayList<>();
    GridView gridView;
    private AdView mAdView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
//        mAdView = view.findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder()
//                .build();
//        mAdView.loadAd(adRequest);
        String status = NetworkUtil.getConnectivityStatusString(getContext());
        if(status=="Internet mavjud emas"){
            //startActivity(new Intent(getActivity(), NoInternetActivity.class));
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new NoInternetFragment()).commit();
        }
        else {
            LoadData(view);
        }
        return  view;
    }

    private void LoadData(View view) {
        gridView=view.findViewById(R.id.gridView);
        getAllNews(view.getContext());
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, NewsViewByIdFragment.newInstance(newsResponseList.get(position))).addToBackStack("NewsFragment").commit();
            }
        });
    }

    public void getAllNews(Context context){
        Call<List<NewsResponse>> newsResponse= ApiClient.getInterface().getAllNews();

        newsResponse.enqueue(new Callback<List<NewsResponse>>() {
            @Override
            public void onResponse(Call<List<NewsResponse>> call, Response<List<NewsResponse>> response) {

                if(response.isSuccessful()){
                    newsResponseList=response.body();
                    CustomAdapter customAdapter=new CustomAdapter(newsResponseList,context);
                    gridView.setAdapter(customAdapter);

                }
                else {
                    String message="Xatolik yuz berdi. keyinroq yana urinib ko'rig";
                    Toast.makeText(context,message,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<NewsResponse>> call, Throwable t) {
                Log.e("onFailure",call.toString());

                String message=t.getLocalizedMessage();
                Toast.makeText(context,message,Toast.LENGTH_LONG).show();
            }
        });
    }
    public class CustomAdapter extends BaseAdapter {
        private List<NewsResponse> newsResponseList;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter(List<NewsResponse> newsResponseList, Context context) {
            this.newsResponseList = newsResponseList;
            this.context = context;
            this.layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return newsResponseList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                convertView=layoutInflater.inflate(R.layout.news_row_grid_items,parent,false);
            }
            ImageView imageView=convertView.findViewById(R.id.imageView);
            TextView textView=convertView.findViewById(R.id.textView);
            LanguageFragment languageFragment=new LanguageFragment();
            String langCode=languageFragment.getLangCode(getContext());
            if(langCode.equals("uz")){
                textView.setText(newsResponseList.get(position).getShort_title_uz());

            }else{
                if(langCode.equals("en")){
                    textView.setText(newsResponseList.get(position).getShort_title_en());
                }
            }
            GlideApp.with(context)
                    .load(newsResponseList.get(position).getImage_url())
                    .into(imageView);
            return convertView;
        }
    }
}