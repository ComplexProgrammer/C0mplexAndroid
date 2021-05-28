package complexprogrammer.uz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsActivity extends AppCompatActivity {

    private List<NewsResponse> newsResponseList=new ArrayList<>();
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        gridView=findViewById(R.id.gridView);
        getAllNews();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(NewsActivity.this,ClickedActivity.class).putExtra("data",newsResponseList.get(position)));

            }
        });
    }
    public void getAllNews(){
        Call<List<NewsResponse>> newsResponse=ApiClient.getInterface().getAllNews();

        Log.e("newsResponse",newsResponse.toString());

        newsResponse.enqueue(new Callback<List<NewsResponse>>() {
            @Override
            public void onResponse(Call<List<NewsResponse>> call, Response<List<NewsResponse>> response) {

                if(response.isSuccessful()){
                    String message="Yulanmoqda...";
                    Toast.makeText(NewsActivity.this,message,Toast.LENGTH_LONG).show();
                    Log.e("call",call.toString());
                    Log.e("response",response.toString());

                    newsResponseList=response.body();
                    CustomAdapter customAdapter=new CustomAdapter(newsResponseList,NewsActivity.this);
                    gridView.setAdapter(customAdapter);

                }
                else {
                    String message="Xatolik yuz berdi. keyinroq yana urinib ko'rig";
                    Toast.makeText(NewsActivity.this,message,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<NewsResponse>> call, Throwable t) {
                Log.e("onFailure",call.toString());

                String message=t.getLocalizedMessage();
                Toast.makeText(NewsActivity.this,message,Toast.LENGTH_LONG).show();
            }
        });
    }
    public class CustomAdapter extends BaseAdapter{
        private List<NewsResponse> newsResponseList;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter(List<NewsResponse> newsResponseList, Context context) {
            this.newsResponseList = newsResponseList;
            this.context = context;
            this.layoutInflater= (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
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