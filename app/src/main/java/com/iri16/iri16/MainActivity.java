package com.iri16.iri16;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {


    private ToggleButton conexion;
    private TextView IP, vab1;
    private EditText DirIP;
    private SeekBar sb1;
    private Button acerca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conexion = (ToggleButton) findViewById(R.id.conexion);
        IP = (TextView) findViewById(R.id.ip);
        DirIP = (EditText) findViewById(R.id.dirIP);
        sb1 = (SeekBar) findViewById(R.id.seekBar);
        acerca = (Button) findViewById(R.id.acercade);

        vab1 = (TextView) findViewById(R.id.textViewSeekbar);


        findViewById(R.id.buttonVerCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, com.iri16.iri16.control.class));
            }
        });


        conexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //codigo cuando presione el boton ;
                if (conexion.isChecked()) {
                    IP.setText(DirIP.getText());
                } else {
                    IP.setText("Desconectado");
                }
            }
        });

        //SeekBar configuracion
        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                String value = String.valueOf(progress);

                try {
                    vab1.setText("seña, 1:" + progress);
                } catch (Throwable e) {

                    e.printStackTrace();
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //efecto sobre el botton acerca de

        acerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Integrandes del grupo IR", Toast.LENGTH_SHORT).show();

            }
        });
        findViewById(R.id.acercade).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, acercaDe.class));
            }
        });

    }


}
