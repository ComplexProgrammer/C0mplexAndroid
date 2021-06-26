package complexprogrammer.uz.ui.games;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import complexprogrammer.uz.R;

public class FlyGameView extends SurfaceView implements Runnable{
    private Thread thread;
    private boolean isPlaying,isGameOver=false;
    private FlyBackground background1,background2;
    private FlyGameActivity activity;
    public static Float screenRatioX,screenRatioY;
    private SharedPreferences preferences;
    private List<Bullet> bullets;
    private Bird[] birds;
    private Random random;
    private SoundPool soundPool;
    private int sound;
    private Paint paint;
    private Flight flight;
    private int screenX,screenY,score=0;
    public FlyGameView(FlyGameActivity activity,int screenX,int screenY) {
        super(activity);
        Log.d("screenX",""+screenX);
        Log.d("screenY","" + screenY);
        this.activity=activity;
        preferences=activity.getSharedPreferences("FlyGame",Context.MODE_PRIVATE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            AudioAttributes audioAttributes=new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();

            soundPool=new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .build();
        }
        else{
            soundPool=new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        }
        sound=soundPool.load(activity, R.raw.shoot,1);

        this.screenX=screenX;
        this.screenY=screenY;
        screenRatioX=1920f/screenX;
        screenRatioY=1080f/screenY;
        background1=new FlyBackground(screenX,screenY,getResources());
        background2=new FlyBackground(screenX,screenY,getResources());

        flight=new Flight(this,screenY,getResources());

        bullets=new ArrayList<>();

        background2.x=screenX;
        paint=new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.WHITE);

        birds=new Bird[4];
        for(int i=0;i<4;i++){
            Bird bird=new Bird(getResources());
            birds[i]=bird;
        }
        random=new Random();
    }

    @Override
    public void run() {
        while (isPlaying){
            update();
            draw();
            sleep();
        }
    }
    private void update(){
        background1.x-=10*screenRatioX;
        background2.x-=10*screenRatioX;
        if (background1.x + background1.backgroud.getWidth() < 0) {
            background1.x=screenX;
        }
        if (background2.x + background2.backgroud.getWidth() < 0) {
            background2.x=screenX;
        }
        if(flight.isGoingUp){
            flight.y-=10*screenRatioY;
        }
        else {
            flight.y+=10*screenRatioY;
        }
        if(flight.y<0){
            flight.y=0;
        }
        if(flight.y>=screenY-flight.height){
            flight.y=screenY-flight.height;
        }

        List<Bullet> trash=new ArrayList<>();
        for(Bullet bullet:bullets){
            if(bullet.x>screenX){
                trash.add(bullet);
            }
            bullet.x+=50*screenRatioX;
            for(Bird bird:birds){
                if(Rect.intersects(bird.getCollisionsShape(),bullet.getCollisinShapt())){
                    score++;
                    bird.x=-500;
                    bullet.x=screenX+500;
                    bird.wasShot=true;
                }
            }
        }
        for(Bullet bullet:trash){
            bullets.remove(bullet);
        }
        for(Bird bird:birds){
            bird.x-=bird.speed;
            if(bird.x+bird.width<0){

//                if(!bird.wasShot){
//                    isGameOver =true;
//                    return;
//                }
                int bound= (int) (30*screenRatioX);
                bird.speed=random.nextInt(bound);
                if(bird.speed<10*screenRatioX){
                    bird.speed= (int) (10*screenRatioX);
                }
                bird.x=screenX;
                bird.y=random.nextInt(screenY-bird.height);

                bird.wasShot=false;
            }
//            if(Rect.intersects(bird.getCollisionsShape(),flight.getCollisionsShape())){
//                isGameOver = true;
//                return;
//            }
        }

    }
    private void draw(){
        if(getHolder().getSurface().isValid()){
            Canvas canvas=getHolder().lockCanvas();
            canvas.drawBitmap(background1.backgroud,background1.x,background1.y,paint);
            canvas.drawBitmap(background2.backgroud,background2.x,background2.y,paint);
            for(Bird bird:birds){
                canvas.drawBitmap(bird.getBird(),bird.x,bird.y,paint);
            }
            canvas.drawText(score+"",screenX/2f,164,paint);
            if(isGameOver){
                isPlaying=false;
                canvas.drawBitmap(flight.getDead(),flight.x,flight.y,paint);
                getHolder().unlockCanvasAndPost(canvas);
                saveIfHighScore();
                waitBeforeExiting();
                return;
            }

            canvas.drawBitmap(flight.getFlight(),flight.x,flight.y,paint);
            for(Bullet bullet:bullets){
                canvas.drawBitmap(bullet.bullet,bullet.x,bullet.y,paint);
            }
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void waitBeforeExiting() {
        try {
            Thread.sleep(1000);
            activity.startActivity(new Intent(activity,FlyActivity.class));
            activity.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void saveIfHighScore() {
        if(preferences.getInt("highscore",0)<score){
            SharedPreferences.Editor editor=preferences.edit();
            editor.putInt("highscore",score);
            editor.apply();
        }

    }

    private void sleep(){
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void resume(){
        isPlaying=true;
        thread=new Thread(this);
        thread.start();
    }
    public void pause(){
        try {
            isPlaying=false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("getX",""+event.getX());
        Log.e("getY",""+event.getY());
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(event.getX()<screenX/2){
                    flight.isGoingUp=true;
                }
                break;
            case MotionEvent.ACTION_UP:
                flight.isGoingUp=false;
                if(event.getX()>screenX/2){
                    flight.toShoot++;
                }
                break;
        }
        return true;
    }
    public static String actionToString(int action) {
        switch (action) {

            case MotionEvent.ACTION_DOWN: return "Down";
            case MotionEvent.ACTION_MOVE: return "Move";
            case MotionEvent.ACTION_POINTER_DOWN: return "Pointer Down";
            case MotionEvent.ACTION_UP: return "Up";
            case MotionEvent.ACTION_POINTER_UP: return "Pointer Up";
            case MotionEvent.ACTION_OUTSIDE: return "Outside";
            case MotionEvent.ACTION_CANCEL: return "Cancel";
        }
        return "";
    }

    public void newBullet() {

        if(!preferences.getBoolean("isMute",false)){
            soundPool.play(sound,1,1,0,0,1);
        }
        Bullet bullet=new Bullet(getResources());
        bullet.x=flight.x+flight.width;
        bullet.y=flight.y+(flight.height)/2;
        bullets.add(bullet);
    }
}