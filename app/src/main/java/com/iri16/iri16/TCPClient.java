package com.iri16.iri16;


import android.util.Log;
import android.widget.TextView;

import com.iri16.iri16.R;

import java.io.*;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.Charset;

public class TCPClient {

    final TextView _tv_estado =(TextView)findViewById(R.id.textview7);  //ip == textview7

	private String serverMessage;
    //public static final String SERVERIP = "192.168.0.5"; //your computer IP address
    public String SERVERIP;

    public static final int SERVERPORT = 2000;
    private OnMessageReceived mMessageListener = null;
    private boolean mRun = false;
 
    PrintWriter out;
    BufferedReader in;
	private Socket socket;
	public boolean con;
	public boolean con2;
   // public TextView estado;

    //TextView textViewc = (TextView) findViewById(R.id.textView5);
	
 
    /**
     *  Constructor of the class. OnMessagedReceived listens for the messages received from server
     */
    public TCPClient(OnMessageReceived listener) {
        mMessageListener = listener;
    }
 
    private TextView findViewById(int textview5) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
     * Sends the message entered by client to the server
     * @param message text entered by client
     */
    public void sendMessage(String message){
        if (out != null && !out.checkError()) {

           // String s = new String(message, Charset.forName(""));
            //System.out.println(s); // Prints as expected
            out.println(message);
            out.flush();

        }
    }

    public String stopClient() throws Throwable{
        mRun = false;
        socket.close();
        con2=socket.isClosed();

        if(con2){
        	con=false;
		return "desconectado";

        }
		return null;
    }

    public String conection() {
        
        if(con){
		return "conectado OK";
        }
		return "error en la conexion";
    }
    
    public void IP(String ip){
        SERVERIP=ip;
        Log.e("TCP Client", SERVERIP);
    }
    
          
    public void run() {
 
        mRun = true;
 
        try {
            //here you must put your computer's IP address.
            InetAddress serverAddr = InetAddress.getByName(SERVERIP);
 
            Log.e("TCP Client", "C: Connecting...");
            //create a socket to make the connection with the server
            socket = new Socket(serverAddr, SERVERPORT);
 
            try {
 
                //send the message to the server
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                
                Log.e("TCP Client", "C: Sent.");
                //_tv_estado.setText("Estado: Conectado");
                Log.e("TCP Client", "C: Done.");
                //textViewc.setText("Done.");
                //receive the message which the server sends back
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                if(socket.isConnected()){
                	con=true;
                   // estado.setText("conectado OK");
                }
                //in this while the client listens for the messages sent by the server
                while (mRun) {
                    serverMessage = in.readLine();
 
                    if (serverMessage != null && mMessageListener != null) {
                        //call the method messageReceived from MyActivity class
                        mMessageListener.messageReceived(serverMessage);
                    }
                    serverMessage = null;
 
                }
 
 
                Log.e("RESPONSE FROM SERVER", "S: Received Message: '" + serverMessage + "'");
 
 
            } catch (Exception e) {
 
                Log.e("TCP", "S: Error", e);
 
            } finally {
                //the socket must be closed. It is not possible to reconnect to this socket
                // after it is closed, which means a new socket instance has to be created.
                socket.close();
                
            }
 
        } catch (Exception e) {
 
            Log.e("TCP", "C: Error", e);
 
        }
		
 
    }
 
    //Declare the interface. The method messageReceived(String message) will must be implemented in the MyActivity
    //class at on asynckTask doInBackground
    public interface OnMessageReceived {
        public void messageReceived(String message);
    }
}