package com.example.practica6.view.comminication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_Singleton {

    private static TCP_Singleton instance;
    private MessageListener obs;
    private ServerSocket ser;
    private Socket sock;
    private InputStream inputStream;
    private OutputStream outputStream;
    private BufferedWriter writer;
    private BufferedReader read;
    private TCP_Singleton() {

    }

    public void SetObserver(MessageListener observer){

        this.obs=observer;

    }



    public static TCP_Singleton getInstance() {

        if (instance == null) {

            instance = new TCP_Singleton();
            instance.start();
        }
        return instance;
    }

    private void start() {
        // TODO Auto-generated method stub

    }

    public void run() {

        try {
            ser = new ServerSocket(5000);
            sock=ser.accept();
            System.out.println("Conectado");
            inputStream = sock.getInputStream();
            outputStream = sock.getOutputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            read = new BufferedReader(inputStreamReader);
            writer = new BufferedWriter( outputStreamWriter);

            while(true) {

                String message = read.readLine();
                obs.Message(message);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void SendMessage(String message){

        new Thread(
                ()->{
                    try {
                        writer.write(message);
                        writer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();

    }
}
