package com.example.user.anasnmnt17_1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;
//creating Mymusic class which extends Service
public class MyMusic extends Service {

    //creating variable for MediaPlayer
    MediaPlayer myPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    //Return an IBinder through which clients can call on to the service.
    // This value  be null.
//Unless you provide binding for your service, you don't need to implement this method, because the default implementation returns null.
    @Override
    public void onCreate() {
        //Called by the system when the service is first created.
        Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show();

        myPlayer = MediaPlayer.create(MyMusic.this,R.raw.song);
        if(myPlayer.isPlaying()){
            Toast.makeText(this, "playing.....", Toast.LENGTH_SHORT).show();
        };
        myPlayer.setLooping(true); // Set looping
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        myPlayer.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        //Called by the system to notify a Service that it is no longer used and is being removed.
        myPlayer.stop();

    }

}