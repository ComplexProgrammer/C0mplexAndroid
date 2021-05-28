package complexprogrammer.uz.ui.news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import complexprogrammer.uz.ApiClient;
import complexprogrammer.uz.ClickedActivity;
import complexprogrammer.uz.GlideApp;
import complexprogrammer.uz.MainActivity;
import complexprogrammer.uz.NewsActivity;
import complexprogrammer.uz.NewsResponse;
import complexprogrammer.uz.R;
import complexprogrammer.uz.ui.home.CustomAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {
    private Button openNewsBtn;
    private List<NewsResponse> newsResponseList=new ArrayList<>();
    GridView gridView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
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
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        gridView=view.findViewById(R.id.gridView);
        getAllNews(view.getContext());
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(view.getContext(), ClickedActivity.class).putExtra("data",newsResponseList.get(position)));

            }
        });

        return  view;
    }
    public void getAllNews(Context context){
        Call<List<NewsResponse>> newsResponse= ApiClient.getInterface().getAllNews();

        Log.e("newsResponse",newsResponse.toString());

        newsResponse.enqueue(new Callback<List<NewsResponse>>() {
            @Override
            public void onResponse(Call<List<NewsResponse>> call, Response<List<NewsResponse>> response) {

                if(response.isSuccessful()){
                    String message="Yulanmoqda...";
                    Toast.makeText(context,message,Toast.LENGTH_LONG).show();
                    Log.e("call",call.toString());
                    Log.e("response",response.toString());

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
                convertView=layoutInflater.inflate(R.layout.row_grid_items,parent,false);
            }
            ImageView imageView=convertView.findViewById(R.id.imageView);
            TextView textView=convertView.findViewById(R.id.textView);
            textView.setText(newsResponseList.get(position).getShort_title_uz());
            GlideApp.with(context)
                    .load(newsResponseList.get(position).getImage_url())
                    .into(imageView);
            return convertView;
        }
    }
}