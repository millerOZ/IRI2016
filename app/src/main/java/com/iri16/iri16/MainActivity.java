package com.iri16.iri16;

import android.app.ActionBar;
import android.content.Intent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.UnsupportedEncodingException;


public class MainActivity extends AppCompatActivity {


    private ToggleButton conexion;
    private TextView IP, vab1,vab2,vab3,vab4,vab5;
    private EditText DirIP;
    private SeekBar sb1,sb2,sb3,sb4,sb5;
    private Button acerca;

    public TCPClient mTcpClient;
    public String SERVERIP = "192.168.43.44";
    private int seekbar1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conexion = (ToggleButton) findViewById(R.id.conexion);
        acerca = (Button) findViewById(R.id.acercade);
        IP = (EditText) findViewById(R.id.ip);
        DirIP = (EditText) findViewById(R.id.dirIP);
        sb1 = (SeekBar) findViewById(R.id.seekBar);
        sb2= (SeekBar)findViewById(R.id.seekBar2);
        sb3= (SeekBar)findViewById(R.id.seekBar3);
        sb4=(SeekBar)findViewById(R.id.seekBar4);
        sb5=  (SeekBar)findViewById(R.id.seekBar5);

        vab1 = (TextView) findViewById(R.id.textViewSeekbar);
        vab2 = (TextView)findViewById(R.id.Textseekbar2);
        vab3 = (TextView)findViewById(R.id.Textseekbar3);
        vab4 = (TextView)findViewById(R.id.Textseekbar4);
        vab5 = (TextView)findViewById(R.id.Textseekbar5);

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
             /*   if (conexion.isChecked()) {
                    new connectTask().execute().toString();
                   SERVERIP = DirIP.getText().toString();

                    DirIP= (EditText) findViewById(R.id.ip);
                } else {
                    try {
                        IP.setText(mTcpClient.stopClient());

                    } catch (Throwable e) {
                        e.printStackTrace();
                    }

                }   */

               //codigo cuando presione el boton ;
                if (conexion.isChecked()) {

                    IP.setText(DirIP.getText());

                } else {
                    IP.setText("Desconectado");
                }
            }
        });

        //SeekBar velocidad
        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                String value = String.valueOf(progress);

                try {
                    vab1.setText("Velocidad,1:" + Integer.toString(progress));

                   seekbar1 = progress;
                    //value1=progress;
                   mensaje();

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

        //SeekBar señal 2
        sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                String value = String.valueOf(progress);

                try {
                    vab2.setText("Señal,1:" + Integer.toString(progress));


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
        //SeekBar señal 3
        sb3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                String value = String.valueOf(progress);

                try {
                    vab3.setText("Señal,1:" + Integer.toString(progress));

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
        //SeekBar señal 4
        sb4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                String value = String.valueOf(progress);

                try {
                    vab4.setText("Señal,1:" + Integer.toString(progress));

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
        //SeekBar señal 5
        sb5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                String value = String.valueOf(progress);

                try {
                    vab5.setText("Señal,1:" + Integer.toString(progress));

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
                Toast.makeText(getApplicationContext(), "fase en desarrollo, ofrecemos disculpas  ", Toast.LENGTH_SHORT).show();

            }
        });
        findViewById(R.id.acercade).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, acercaDe.class));
            }
        });

    }

    public void mensaje()
    {
        byte[] b= {(byte) 126,(byte) seekbar1};
        String s=" ";
        try{
            s = new String(b,"UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        if(mTcpClient != null){
            mTcpClient.sendMessage(s);
            IP.setText(mTcpClient.conection());
        }

    }

public class connectTask extends AsyncTask<String,String,TCPClient>
    {

        @Override
        protected TCPClient doInBackground(String... message) {
   //creamos el objeto TCPClient
            mTcpClient = new TCPClient(new TCPClient.OnMessageReceived() {
                @Override
                public void messageReceived(String message) {

                    publishProgress(message);
                }
            });
            mTcpClient.IP(SERVERIP);
            mTcpClient.run();
            return null;
        }
    }


}
