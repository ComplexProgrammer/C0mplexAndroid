package complexprogrammer.uz.ui.games;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import complexprogrammer.uz.R;

import static complexprogrammer.uz.ui.games.FlyGameView.screenRatioX;
import static complexprogrammer.uz.ui.games.FlyGameView.screenRatioY;

public class Bullet {
    int x,y,width,height;
    Bitmap bullet;
    Bullet(Resources resources){
        bullet= BitmapFactory.decodeResource(resources, R.drawable.bullet);
        width=bullet.getWidth();
        height=bullet.getHeight();

        width/=4;
        height/=4;

        width*=screenRatioX;
        height*=screenRatioY;

        bullet=Bitmap.createScaledBitmap(bullet,width,height,false);
    }
    Rect getCollisinShapt(){
        return new Rect(x,y,x+width,y+height);
    }
}
