package complexprogrammer.uz.ui.games;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bumptech.glide.load.engine.Resource;

import complexprogrammer.uz.R;

public class FlyBackground {
    int x=0,y=0;
    Bitmap backgroud;
    public FlyBackground(int screenX, int screenY, Resources resources){
        backgroud= BitmapFactory.decodeResource(resources, R.drawable.background);
        backgroud=Bitmap.createScaledBitmap(backgroud,screenX,screenY,false);
    }
}
