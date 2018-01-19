package com.example.user.anasnmnt17_1;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
//created main class called Mainactivity which extends appCompactactivity
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //declaring widgets variables
    Button startButton, stopButton;
    MediaPlayer myPlayer;
    NotificationCompat.Builder builder;

    //onCreate method is called by  creating main activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//implementing button id's from the layout xml file which alocates the id
        startButton = (Button) findViewById(R.id.start_button);
        Intent intent= new Intent(MainActivity.this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);

         builder= new NotificationCompat.Builder(MainActivity.this);
        builder.setContentTitle("music player");
        builder.setContentText("playing music");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);
        myPlayer = MediaPlayer.create(MainActivity.this,R.raw.song);
        if(myPlayer.isPlaying()){
            Toast.makeText(this, "playing.....", Toast.LENGTH_SHORT).show();
        };
        myPlayer.setLooping(true);
       stopButton = (Button) findViewById(R.id.stop_button);
        //setting onclick listener by clicking start button to it displays the toast of the particular action
        startButton.setOnClickListener(MainActivity.this);
        //setting onclick listener by clicking stop button to it displays the toast of the particular action
        stopButton.setOnClickListener(MainActivity.this);

    }
    //implementing onClick view method
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_button:// Play audio from the start service intent of context MyMusic.class
              // Set looping
             //   myPlayer.setLooping(true);
               myPlayer.start();
                NotificationManager notificationManager= (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(2,builder.build());
                Toast.makeText(this, " Music Started", Toast.LENGTH_LONG).show();

                break;
            case R.id.stop_button:// Pause audio
             myPlayer.stop();
              //  myPlayer.setLooping(false);
                Toast.makeText(this, " Music Stopped", Toast.LENGTH_LONG).show();
                break;
        }
    }



    @Override
    protected void onStop() {
        super.onStop();
        startService(new Intent(this,MyMusic.class));
    }
}
