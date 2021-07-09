package complexprogrammer.uz.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import complexprogrammer.uz.NoInternetFragment;
import complexprogrammer.uz.R;
import complexprogrammer.uz.models.NetworkUtil;
import complexprogrammer.uz.services.GlideApp;
import complexprogrammer.uz.ui.LanguageFragment;


public class HomeFragment extends Fragment {




    // ArrayList for person names, email Id's and mobile numbers
    ArrayList<String>  imageUrls= new ArrayList<>();
    ArrayList<String> titles_uz = new ArrayList<>();
    ArrayList<String> titles_en = new ArrayList<>();
    ArrayList<String> texts_uz = new ArrayList<>();
    ArrayList<String> texts_en = new ArrayList<>();
    private final static String TAG_FRAGMENT = "TAG_HOME_FRAGMENT";
    private AdView mAdView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mAdView = root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);



        String status = NetworkUtil.getConnectivityStatusString(getContext());
        if(status=="Internet mavjud emas"){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new NoInternetFragment()).commit();
        }
        else {
            LoadData(root);
        }
//        Toast.makeText(getContext(), status, Toast.LENGTH_LONG).show();

        return root;
    }


    public void LoadData(View root){
        CustomAdapter customAdapter = new CustomAdapter(root.getContext(), titles_uz,titles_en,texts_uz,texts_en,imageUrls);


        // get the reference of Recycl   erView
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        final String Url="http://complexprogrammer.uz:4444/Api/C0mplexApi/GetNews";
        RequestQueue requestQueue= Volley.newRequestQueue(this.getActivity());

        JsonArrayRequest objectRequest = new JsonArrayRequest (
                Request.Method.GET,
                Url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // implement for loop for getting users list data
                            for (int i = 0; i < response.length(); i++) {

                                // create a JSONObject for fetching single user data
                                JSONObject jsonDetail = response.getJSONObject(i);
                                // fetch email and name and store it in arraylist
                                imageUrls.add(jsonDetail.getString("image_url"));
                                titles_uz.add(jsonDetail.getString("short_title_uz"));
                                titles_en.add(jsonDetail.getString("short_title_en"));
                                texts_uz.add(jsonDetail.getString("text_uz"));
                                texts_en.add(jsonDetail.getString("text_en"));
                            }
                            //  call the constructor of CustomAdapter to send the reference and data to Adapter

                            recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Xatolik",error.toString());
                    }
                }
        );
        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(objectRequest);
    }
    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
        private ArrayList<String> titles_uz;
        private ArrayList<String> titles_en;
        private ArrayList<String> texts_uz;
        private ArrayList<String> texts_en;
        private ArrayList<String> imageUrls;
        private final Context context;
        public CustomAdapter(Context context, ArrayList<String> titles_uz,ArrayList<String> titles_en, ArrayList<String> texts_uz, ArrayList<String> texts_en, ArrayList<String> imageUrls) {
            this.context = context;
            this.titles_uz = titles_uz;
            this.titles_en = titles_en;
            this.imageUrls = imageUrls;
            this.texts_uz = texts_uz;
            this.texts_en = texts_en;
        }
        @Override
        public CustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // infalte the item Layout
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_row_grid_items, parent, false);
            CustomAdapter.MyViewHolder vh = new CustomAdapter.MyViewHolder(v); // pass the view to View Holder
            return vh;
        }


        @Override
        public void onBindViewHolder(CustomAdapter.MyViewHolder holder,final int position) {
            // set the data in items
            GlideApp.with(context).load(imageUrls.get(position)).into(holder.imageView);
            LanguageFragment languageFragment=new LanguageFragment();
            String langCode=languageFragment.getLangCode(getContext());
            if(langCode.equals("uz")){
                holder.title.setText(titles_uz.get(position));

            }else{
                if(langCode.equals("en")){
                    holder.title.setText(titles_en.get(position));
                }
            }
            // implement setOnClickListener event on item view.
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    HomeViewModel homeViewModel = new HomeViewModel();
                    homeViewModel.title_uz=titles_uz.get(position);
                    homeViewModel.title_en=titles_en.get(position);
                    homeViewModel.text_uz=texts_uz.get(position);
                    homeViewModel.text_en=texts_en.get(position);
                    homeViewModel.image_url=imageUrls.get(position);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, HomeViewByIdFragment.newInstance(homeViewModel)).addToBackStack("HomeFragment").commit();

                }
            });
        }

        @Override
        public int getItemCount() {
            return titles_uz.size();
        }
        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView title, text;// init the item view's
            ImageView imageView;
            public MyViewHolder(View itemView) {
                super(itemView);

                // get the reference of item view's
                imageView = (ImageView) itemView.findViewById(R.id.imageView);
                title = (TextView) itemView.findViewById(R.id.title);
            }
        }
    }
}