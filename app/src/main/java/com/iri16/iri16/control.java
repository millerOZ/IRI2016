package com.iri16.iri16;

import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;

public class control extends AppCompatActivity {

    private ImageButton up, stop;
    private Switch musica;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);


        up = (ImageButton)findViewById(R.id.imageButtonUp);
        stop = (ImageButton)findViewById(R.id.imageButtonStop);
        musica = (Switch)findViewById(R.id.musica);

        final MediaPlayer sound = MediaPlayer.create(control.this, R.raw.audio);
      //  final MediaPLayer soundFondo = MediaPlayer.create(control.this,R.raw.audio1);





        up.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {

                sound.start();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound.pause();
            }
        });



    }
}
