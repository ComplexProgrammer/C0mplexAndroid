package complexprogrammer.uz.ui.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
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
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import complexprogrammer.uz.R;




public class HomeFragment extends Fragment {

    // ArrayList for person names, email Id's and mobile numbers
    ArrayList<String> guid = new ArrayList<>();
    ArrayList<String>  image_url= new ArrayList<>();
    ArrayList<Integer>  img_url= new ArrayList<>();
    ArrayList Images = new ArrayList<>(Arrays.asList(R.drawable.ic_menu_camera,R.drawable.icon_72x72));
    ArrayList<String> short_title_uz = new ArrayList<>();
    ArrayList<String> long_title_uz = new ArrayList<>();
    private android.widget.ImageView imageView;
    private HomeViewModel homeViewModel;
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        //homeViewModel =new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        CustomAdapter customAdapter = new CustomAdapter(root.getContext(), guid,image_url, Images, short_title_uz, long_title_uz);
//        Log.e("container",container.toString());
        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//                Log.e("onChanged",s.toString());
//            }
//        });

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
                        //Log.e("Rest",response.toString());
                        try {
                            // get JSONObject from JSON file
                            //JSONObject obj = new JSONObject(response.toString());
                            Log.e("response",response.toString());
                            // fetch JSONArray named users
                            //JSONArray userArray = response.getJSONArray("");
                            //Log.e("Array",userArray.toString());
                            // implement for loop for getting users list data
                            for (int i = 0; i < response.length(); i++) {

                                // create a JSONObject for fetching single user data
                                JSONObject jsonDetail = response.getJSONObject(i);
                                // fetch email and name and store it in arraylist
                                guid.add(jsonDetail.getString("guid"));
                                image_url.add(jsonDetail.getString("image_url"));

                                //loadImage(jsonDetail.getString("image_url"));
                                short_title_uz.add(jsonDetail.getString("short_title_uz"));
                                long_title_uz.add(jsonDetail.getString("long_title_uz"));
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
//        String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};
//        ListView listView = (ListView) root.findViewById(R.id.simpleListView);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.activity_listview, R.id.textView, countryList);
//        listView.setAdapter(arrayAdapter);

        return root;
    }
    private void loadImage(String url){
        Glide.with(this.imageView)
                .load(url)
                .error(R.drawable.icon_72x72)
                .into(imageView);
    }

}