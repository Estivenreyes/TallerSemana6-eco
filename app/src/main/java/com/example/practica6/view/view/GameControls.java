package com.example.practica6.view.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;

import com.example.practica6.R;
import com.example.practica6.view.comminication.MessageListener;
import com.example.practica6.view.comminication.TCP_Singleton;
import com.example.practica6.view.model.Direction;
import com.google.gson.Gson;
import com.sokah.parcial1.R;
import com.sokah.parcial1.communication.OnMessageListener;
import com.sokah.parcial1.communication.TCP_Singleton;
import com.sokah.parcial1.model.Colors;
import com.sokah.parcial1.model.Direction;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements MessageListener {

    private Button up,down,left,right,btncolor;
    private TCP_Singleton tcp;
    private String message;
    private Direction dir;
    private Gson gson;
    private Boolean buttonPressed;
    private int r,g,b;
    private int sleep=50;
    Color colors;
    @SuppressLint("ClickableViewAccessibility")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tcp= TCP_Singleton.getInstance();
        tcp.SetObserver(this);
        setContentView(R.layout.activity_game_controls);
        btncolor=findViewById(R.id.buttonColor);
        up= findViewById(R.id.buttonUp);
        down = findViewById(R.id.buttonDown);
        left = findViewById(R.id.buttonLeft);
        right = findViewById(R.id.buttonRight);
        SetRandomColor();
        gson = new Gson();

        btncolor.setOnClickListener(
                (v)->{

                    //cambia color circulo
                    btncolor.setBackgroundColor(Color.rgb(r,g,b));
                    up.setBackgroundColor(Color.rgb(r,g,b));
                    down.setBackgroundColor(Color.rgb(r,g,b));
                    left.setBackgroundColor(Color.rgb(r,g,b));
                    right.setBackgroundColor(Color.rgb(r,g,b));
                    colors = new Color(r,g,b);
                    String message = gson.toJson(colors);
                    tcp.SendMessage(message);
                    SetRandomColor();

                }
        );


        //Controles
        up.setOnTouchListener(
                (view,event)->{

                    switch (event.getAction()){

                        case MotionEvent.ACTION_DOWN:
                            buttonPressed=true;
                            new Thread(


                                    ()->{

                                        while (buttonPressed) {
                                            dir = new Direction(2);
                                            message = gson.toJson(dir);
                                            tcp.SendMessage(message );
                                            try {
                                                Thread.sleep(sleep);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                            ).start();


                            break;

                        case MotionEvent.ACTION_UP:

                            buttonPressed=false;

                            break;
                    }
                    return true;
                }
        );
        down.setOnTouchListener(
                (view,event)->{

                    switch (event.getAction()){

                        case MotionEvent.ACTION_DOWN:

                            buttonPressed=true;
                            new Thread(

                                    ()->{

                                        while (buttonPressed) {
                                            dir = new Direction(-2);
                                            message = gson.toJson(dir);
                                            tcp.SendMessage(message );
                                            try {
                                                Thread.sleep(sleep);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                            ).start();


                            break;

                        case MotionEvent.ACTION_UP:

                            buttonPressed=false;

                            break;
                    }
                    return true;
                }

        );
        right.setOnTouchListener(
                (view,event)->{
                    switch (event.getAction()){

                        case MotionEvent.ACTION_DOWN:
                            buttonPressed=true;
                            new Thread(

                                    ()->{

                                        while (buttonPressed) {
                                            dir = new Direction(1);
                                            message = gson.toJson(dir);
                                            tcp.SendMessage(message);
                                            try {
                                                Thread.sleep(sleep);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                            ).start();


                            break;

                        case MotionEvent.ACTION_UP:

                            buttonPressed=false;

                            break;
                    }
                    return true;
                }

        );
        left.setOnTouchListener(
                (view,event)->{
                    switch (event.getAction()){

                        case MotionEvent.ACTION_DOWN:

                            buttonPressed=true;
                            new Thread(

                                    ()->{

                                        while (buttonPressed) {
                                            dir = new Direction(-1);
                                            message = gson.toJson(dir);
                                            tcp.SendMessage(message);
                                            try {
                                                Thread.sleep(sleep);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }
                            ).start();


                            break;

                        case MotionEvent.ACTION_UP:

                            buttonPressed=false;

                            break;
                    }
                    return true;
                    ///ese era el problema no aparece ni buscando en las ventanas
                }

        );

    }

    @Override
    public void OnMessage(String msg) {

    }

    public void SetRandomColor(){
        Random random = new Random();
        r=random.nextInt((255-1)+1)+1;
        g=random.nextInt((255-1)+1)+1;
        b=random.nextInt((255-1)+1)+1;


    }

    @Override
    public void Message(String msg) {

    }
}