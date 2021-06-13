package complexprogrammer.uz.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import complexprogrammer.uz.R;
import complexprogrammer.uz.models.NetworkUtil;
import complexprogrammer.uz.services.GlideApp;


public class HomeFragment extends Fragment {

    // ArrayList for person names, email Id's and mobile numbers
    ArrayList<String>  imageUrls= new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> texts = new ArrayList<>();
    NetworkUtil networkUtil=new NetworkUtil();
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        String status = NetworkUtil.getConnectivityStatusString(getContext());
        ImageView imageView=root.findViewById(R.id.no_internet);
        if(status=="No internet is available"){
            imageView.setVisibility(View.VISIBLE);
        }
        else {
            imageView.setVisibility(View.INVISIBLE);
            LoadData(root);
        }
        Toast.makeText(getContext(), status, Toast.LENGTH_LONG).show();
        return root;
    }


    public void LoadData(View root){
        CustomAdapter customAdapter = new CustomAdapter(root.getContext(), titles,texts,imageUrls);


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
                                titles.add(jsonDetail.getString("short_title_uz"));
                                texts.add(jsonDetail.getString("text_uz"));
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
        private ArrayList<String> titles;
        private ArrayList<String> texts;
        private ArrayList<String> imageUrls;
        private final Context context;
        public CustomAdapter(Context context, ArrayList<String> titles, ArrayList<String> texts, ArrayList<String> imageUrls) {
            this.context = context;
            this.titles = titles;
            this.imageUrls = imageUrls;
            this.texts = texts;
        }
        @Override
        public CustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // infalte the item Layout
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_row_grid_items, parent, false);
            CustomAdapter.MyViewHolder vh = new CustomAdapter.MyViewHolder(v); // pass the view to View Holder
            return vh;
        }

//        @Override
//        public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
//
//        }

        @Override
        public void onBindViewHolder(CustomAdapter.MyViewHolder holder,final int position) {
            // set the data in items
            GlideApp.with(context).load(imageUrls.get(position)).into(holder.imageView);
            holder.title.setText(titles.get(position));
            // implement setOnClickListener event on item view.
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // display a toast with person name on item click
                    Toast.makeText(context, titles.get(position), Toast.LENGTH_SHORT).show();
                    HomeViewModel homeViewModel = new HomeViewModel();
                    homeViewModel.title=titles.get(position);
                    homeViewModel.text=texts.get(position);
                    homeViewModel.image_url=imageUrls.get(position);
                    Log.e("homeViewModel",homeViewModel.title);
                Intent intent = new Intent(getActivity(), HomeViewByIdActivity.class).putExtra("data", homeViewModel);
                startActivity(intent);
                }
            });
        }
        @Override
        public int getItemCount() {
            return titles.size();
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