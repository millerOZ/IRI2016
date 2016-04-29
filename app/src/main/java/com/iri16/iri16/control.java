package com.iri16.iri16;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.media.MediaPlayer;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class control extends AppCompatActivity {

    private ImageButton up, stop;
    private Switch musica;
    Handler bluertoothIn;

    final int handlerState = 0; //usado para identificar msm handler
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private StringBuilder recDataString = new StringBuilder();

    private ConnectedThread mConnectedTheread;

   //SPP UUID service - funciona para la mayoria de dispositivos
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    TextView txtString;

    private static String address = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);


        up = (ImageButton)findViewById(R.id.imageButtonUp);
        stop = (ImageButton)findViewById(R.id.imageButtonStop);
      //  musica = (Switch)findViewById(R.id.musica);
       // txtString = (TextView)findViewById(R.id.txtString);

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

        bluertoothIn = new Handler()
        {
          public void handMessage(android.os.Message msg)
          {
              if(msg.what == handlerState) //el msm es lo que queremos
              {
                  String readMessage = (String)msg.obj;
                  recDataString.append(readMessage);
                  int endOfLineIndex = recDataString.indexOf("~");//fin de linea

                  if(endOfLineIndex > 0)
                  {
                      String dataInprinln = recDataString.substring(0,endOfLineIndex);
                      txtString.setText("Datos recibidos = "+ dataInprinln);
                  }
              }
          }
        };

    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException
    {  //crea una conexion segura con BT usando UUID
        return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
    }
    private class ConnectedThread extends Thread
    {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;


        //creacion de la conexion del hilo
        public ConnectedThread(BluetoothSocket socket)
        {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try{//creacion de I/0 stream para la conexion
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            }catch(IOException e){ }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run()
        {
            byte[] buffer = new byte[256];
            int bytes;

            //seguira escuchando los msm recibidos
            while(true)
            {
                try{
                    bytes = mmInStream.read(buffer); //lectura de bytes para entrada buffer
                    String readMessage = new String(buffer,0,bytes);
                    //envio de bytes obtenidos en la UI Activity via handler
                    bluertoothIn.obtainMessage(handlerState,bytes,-1,readMessage).sendToTarget();
                }catch(IOException e){
                    break;
                }
            }
        }
        public void write(String input)
        {
            byte[] msgBuffer = input.getBytes(); //convierte la String en bytes
            try{
                mmOutStream.write(msgBuffer);   //escribe bytes la conexion BT via outstream
            }catch (IOException e){
                Toast.makeText(getBaseContext(),"la conexion fallo",Toast.LENGTH_LONG).show();
                finish();
            }
        }

    }
}
