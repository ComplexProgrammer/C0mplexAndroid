package complexprogrammer.uz.ui.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import complexprogrammer.uz.GlideApp;
import complexprogrammer.uz.MainActivity;
import complexprogrammer.uz.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    private ArrayList<String> personNames;
    private ArrayList<String> imageUrls;
    private ArrayList Images;
    private ArrayList<String> emailIds;
    private ArrayList<String> mobileNumbers;
   private final Context context;
    public CustomAdapter(Context context, ArrayList<String> personNames, ArrayList<String> imageUrls,ArrayList Images, ArrayList<String> emailIds, ArrayList<String> mobileNumbers) {
        this.context = context;
        this.personNames = personNames;
        this.imageUrls = imageUrls;
        this.Images = Images;

        this.emailIds = emailIds;
        this.mobileNumbers = mobileNumbers;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // set the data in items
        holder.name.setText(personNames.get(position));
//        Pattern p=Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
//        Matcher m=p.matcher(imageUrls.get(position));
//        List<String> tokens=new ArrayList<>();
//        while (m.find()){
//            String token=m.group(1);
//            tokens.add(token);
//        }
        String img_url=imageUrls.get(position);
         Log.e("img_url",img_url);
//        GlideApp.with(context).load(img_url).into(holder.imageView);
//        holder.imageView=context
//        Glide.with(context)
//                .load(img_url)
//                .error(R.drawable.icon_72x72)
//                .into(holder.imageView);
//        holder.imageView.setImageResource(R.drawable.ic_menu_camera);
        //holder.imageView.setImageResource(img_urls.get(position));


       // Uri url=Uri.parse(imageUrls.get(position));
        // Log.e("imageUrls",imageUrls.get(position).toString());

        //Picasso.get().load(imageUrls.get(position)).into(holder.imageView);


        holder.email.setText(emailIds.get(position));
        holder.mobileNo.setText(mobileNumbers.get(position));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                Toast.makeText(context, personNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return personNames.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, mobileNo;// init the item view's
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            String url="http://complexprogrammer.uz:4444/Files/News/Photos/5195764f-8240-4eed-9c1c-2287ec764c0d.jpeg";

//            Picasso.get().load(url).into(imageView);
            name = (TextView) itemView.findViewById(R.id.guid);
            email = (TextView) itemView.findViewById(R.id.short_title_uz);
            mobileNo = (TextView) itemView.findViewById(R.id.long_title_uz);
        }
    }
    private class LoadImage extends AsyncTask<String,Void, Bitmap> {
        ImageView imageView;
        String url;
        public LoadImage(ImageView ivResult,String url){
            this.imageView=ivResult;
           this.url=url;
        }
        @Override
        protected Bitmap doInBackground(String... strings) {
            String urlLink=strings[0];
            Bitmap bitmap=null;
            try {

                InputStream inputStream=new java.net.URL(urlLink).openStream();
                bitmap= BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                Log.e("error",e.toString());

                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            //imgView.setImageBitmap(bitmap);
        }
    }
}
