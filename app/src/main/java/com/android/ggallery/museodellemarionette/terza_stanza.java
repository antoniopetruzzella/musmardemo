package com.android.ggallery.museodellemarionette;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class terza_stanza extends AppCompatActivity implements SurfaceHolder.Callback, View.OnClickListener{

    private MediaPlayer antoVideoPlayer,currentMediaPlaying,audioguida1,audioguida2,audioguida3;
    private SurfaceHolder holder;
    private SurfaceView surface;
    private ImageButton videoplaybtn,gpplaybtn,gppausebtn,palazzobalbibtn,lacollezionebtn,videointrobtn,audiostop1,audiostop2,audiostop3;
    private ImageView surfaceview;
    private SeekBar timeline;
    private TextView guardailvideo,ascoltaaudio,ag1,ag2,ag3;
    private Intent palazzobalbiIntent, videoIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terza_stanza);
        surface = (SurfaceView) findViewById(R.id.surfaceview);
        videoplaybtn=(ImageButton)findViewById(R.id.videoplaybtn);
        gpplaybtn=(ImageButton)findViewById(R.id.gpplaybtn);
        gppausebtn=(ImageButton)findViewById(R.id.gppausebtn);
        surfaceview=(ImageView)findViewById(R.id.imageView3);
        palazzobalbibtn=(ImageButton)findViewById(R.id.palazzobalbibtn);
        lacollezionebtn=(ImageButton)findViewById(R.id.lacollezionebtn);
        videointrobtn=(ImageButton)findViewById(R.id.videointrobtn);
        ag1=(TextView)findViewById(R.id.ag1);
        ag2=(TextView)findViewById(R.id.ag2);
        ag3=(TextView)findViewById(R.id.ag3);
        audiostop1=(ImageButton)findViewById(R.id.audiostop1);
        audiostop2=(ImageButton)findViewById(R.id.audiostop2);
        audiostop3=(ImageButton)findViewById(R.id.audiostop3);
        videoplaybtn.setOnClickListener(this);
        gpplaybtn.setOnClickListener(this);
        gppausebtn.setOnClickListener(this);
        ag1.setOnClickListener(this);
        ag2.setOnClickListener(this);
        ag3.setOnClickListener(this);
        palazzobalbibtn.setOnClickListener(this);
        lacollezionebtn.setOnClickListener(this);
        videointrobtn.setOnClickListener(this);
        holder = surface.getHolder();
        holder.addCallback(this);
        palazzobalbiIntent=new Intent(this, PalazzoBalbi.class);
        videoIntent=new Intent(this, Video.class);
        timeline=(SeekBar)findViewById(R.id.timeline);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/mbold.ttf");
        guardailvideo=(TextView)findViewById(R.id.guardailvideo);
        ascoltaaudio=(TextView)findViewById(R.id.ascoltaaudio);
        guardailvideo.setTypeface(type);
        ascoltaaudio.setTypeface(type);
        audioguida1=MediaPlayer.create(this, R.raw.sala03_01);
        audioguida2=MediaPlayer.create(this, R.raw.sala03_02);
        audioguida3=MediaPlayer.create(this, R.raw.sala03_03);
        Global global=(Global)getApplicationContext();
        global.setInStaticActivity(true);
        handler.post(runnable);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        antoVideoPlayer=MediaPlayer.create(this, R.raw.sala03);
        antoVideoPlayer.setDisplay(holder);

    }


    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    protected void onPause(){

        antoVideoPlayer.stop();
        if(currentMediaPlaying!=null) {
            currentMediaPlaying.stop();
        }
        audioguida1.stop();
        audioguida2.stop();
        audioguida3.stop();
        super.onPause();


    }

    @Override
    protected void onStop(){

        handler.removeCallbacks(runnable);
        antoVideoPlayer.release();
        if(currentMediaPlaying!=null) {
            currentMediaPlaying.release();
        }
        audioguida1.release();
        audioguida2.release();
        audioguida3.release();
        super.onStop();

    }

    @Override
    protected void onResume(){

        antoVideoPlayer=MediaPlayer.create(this, R.raw.sala03);
        currentMediaPlaying=null;
        audioguida1=MediaPlayer.create(this, R.raw.sala03_01);
        audioguida2=MediaPlayer.create(this, R.raw.sala03_02);
        audioguida3=MediaPlayer.create(this, R.raw.sala03_03);
        surfaceview.setVisibility(View.VISIBLE);
        videoplaybtn.setVisibility(View.VISIBLE);
        handler.post(runnable);
        Global global=(Global)getApplicationContext();
        global.setInStaticActivity(false);
        super.onResume();
    }

    @Override
    public  void onClick(View view){

        switch (view.getId()){

            case R.id.videoplaybtn:
                timeline.setMax(antoVideoPlayer.getDuration());
                if(audioguida1.isPlaying()) {
                    audioguida1.pause();
                }
                if(audioguida2.isPlaying()) {
                    audioguida2.pause();
                }
                if(audioguida3.isPlaying()) {
                    audioguida3.pause();
                }
                antoVideoPlayer.start();
                videoplaybtn.setVisibility(View.INVISIBLE);
                surfaceview.setVisibility(View.INVISIBLE);
                break;

            case R.id.gppausebtn:
                if(currentMediaPlaying!=null) {
                    currentMediaPlaying.pause();
                }
                if(currentMediaPlaying!=null) {
                    if (currentMediaPlaying == antoVideoPlayer) {
                        videoplaybtn.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.gpplaybtn:
                if(antoVideoPlayer.isPlaying()){
                    antoVideoPlayer.pause();
                }
                if(audioguida2.isPlaying()) {
                    audioguida2.pause();
                }
                if(audioguida3.isPlaying()) {
                    audioguida3.pause();
                }
                if(audioguida1.isPlaying()) {
                    audioguida1.pause();
                }
                if(currentMediaPlaying!=null) {
                    currentMediaPlaying.start();
                }
                if(currentMediaPlaying!=null) {
                    if (currentMediaPlaying == antoVideoPlayer) {
                        videoplaybtn.setVisibility(View.INVISIBLE);
                    }
                }
                break;
            case R.id.ag1:
                if(audioguida1!=null) {
                    timeline.setMax((audioguida1.getDuration()));
                    if(antoVideoPlayer.isPlaying()){
                        antoVideoPlayer.pause();
                    }
                    if(audioguida2.isPlaying()) {
                        audioguida2.pause();
                    }
                    if(audioguida3.isPlaying()) {
                        audioguida3.pause();
                    }
                    audioguida1.start();
                    videoplaybtn.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.ag2:
                timeline.setMax((audioguida2.getDuration()));
                if(antoVideoPlayer.isPlaying()){
                    antoVideoPlayer.pause();
                }
                if(audioguida1.isPlaying()) {
                    audioguida1.pause();
                }
                if(audioguida3.isPlaying()) {
                    audioguida3.pause();
                }
                audioguida2.start();
                videoplaybtn.setVisibility(View.VISIBLE);
                break;
            case R.id.ag3:
                timeline.setMax((audioguida3.getDuration()));
                if(antoVideoPlayer.isPlaying()){
                    antoVideoPlayer.pause();
                }
                if(audioguida2.isPlaying()) {
                    audioguida2.pause();
                }
                if(audioguida1.isPlaying()) {
                    audioguida1.pause();
                }
                audioguida3.start();
                videoplaybtn.setVisibility(View.VISIBLE);
                break;
            case R.id.palazzobalbibtn:
                palazzobalbiIntent.putExtra("indirizzo","palazzobalbi.html");
                startActivity(palazzobalbiIntent);
                break;
            case R.id.lacollezionebtn:
                palazzobalbiIntent.putExtra("indirizzo","collezione.html");
                startActivity(palazzobalbiIntent);
                break;
            case R.id.videointrobtn:
                startActivity(videoIntent);
                break;



        }



    }

    private Runnable runnable = new Runnable()
    {

        @Override
        public void run( )
        {


            try {
                Global global=(Global)getApplicationContext();
                global.setCurrentMediaPlaying(null);
                audiostop1.setImageResource(R.drawable.audiostop);
                audiostop2.setImageResource(R.drawable.audiostop);
                audiostop3.setImageResource(R.drawable.audiostop);


                if (antoVideoPlayer != null) {
                    if (antoVideoPlayer.isPlaying()) {
                        currentMediaPlaying=antoVideoPlayer;
                        global.setCurrentMediaPlaying(antoVideoPlayer);
                        timeline.setProgress(antoVideoPlayer.getCurrentPosition());
                    }
                }

                if (audioguida1 != null) {

                    if (audioguida1.isPlaying()) {
                        currentMediaPlaying=audioguida1;
                        global.setCurrentMediaPlaying(audioguida1);
                        timeline.setProgress(audioguida1.getCurrentPosition());
                        audiostop1.setImageResource(R.drawable.audioplay);
                    }
                }

                if (audioguida2 != null) {
                    if (audioguida2.isPlaying()) {
                        currentMediaPlaying=audioguida2;
                        global.setCurrentMediaPlaying(audioguida2);
                        timeline.setProgress(audioguida2.getCurrentPosition());
                        audiostop2.setImageResource(R.drawable.audioplay);
                    }
                }

                if (audioguida3 != null) {
                    if (audioguida3.isPlaying()) {
                        currentMediaPlaying=audioguida3;
                        global.setCurrentMediaPlaying(audioguida3);
                        timeline.setProgress(audioguida3.getCurrentPosition());
                        audiostop3.setImageResource(R.drawable.audioplay);
                    }
                }

            }catch (Exception ex){}

            handler.postDelayed( this, 50);
        }
    };

    Handler handler =new Handler()
    {
        @Override
        public void handleMessage(Message mess){

        }

    };
}
