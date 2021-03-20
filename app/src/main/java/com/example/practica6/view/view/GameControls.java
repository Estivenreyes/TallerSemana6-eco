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
;

import java.util.Random;

class GameActivity extends AppCompatActivity implements MessageListener {

    private Button botonArriba,botonAbajo,botonIzquierda,botonDerecha,cambiarColor;
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
        cambiarColor=findViewById(R.id.cambiarColor);
        botonArriba= findViewById(R.id.botonArriba);
        botonAbajo = findViewById(R.id.botonAbajo);
        botonIzquierda = findViewById(R.id.botonIzquierda);
        botonDerecha = findViewById(R.id.botonDerecha);
        SetRandomColor();
        gson = new Gson();

        cambiarColor.setOnClickListener(
                (v)->{

                    //cambia color circulo
                    cambiarColor.setBackgroundColor(Color.rgb(r,g,b));
                    botonArriba.setBackgroundColor(Color.rgb(r,g,b));
                    botonAbajo.setBackgroundColor(Color.rgb(r,g,b));
                    botonIzquierda.setBackgroundColor(Color.rgb(r,g,b));
                    botonDerecha.setBackgroundColor(Color.rgb(r,g,b));
                    colors = new Color();
                    String message = gson.toJson(colors);
                    tcp.SendMessage(message);
                    SetRandomColor();

                }
        );


        //Controles
        botonArriba.setOnTouchListener(
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
        botonAbajo.setOnTouchListener(
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
        botonDerecha.setOnTouchListener(
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
        botonIzquierda.setOnTouchListener(
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