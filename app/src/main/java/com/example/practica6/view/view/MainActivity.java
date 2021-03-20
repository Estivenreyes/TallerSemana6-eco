package com.example.practica6.view.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.practica6.R;
import com.example.practica6.view.comminication.MessageListener;
import com.example.practica6.view.comminication.TCP_Singleton;
import com.example.practica6.view.model.Name;
import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity implements MessageListener {

    EditText NameContent;
    Button buttonNext;
    TCP_Singleton tcp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tcp = TCP_Singleton.getInstance();
        tcp.SetObserver(this);
        NameContent = findViewById(R.id.NameContent);
        buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(
                (v) -> {
                    if(NameContent.getText().length()>0) {
                        Intent intent = new Intent(this, GameActivity.class);
                        Gson gson = new Gson();
                        Name name = new Name(NameContent.getText().toString());
                        String message = gson.toJson(name);
                        tcp.SendMessage(message);
                        startActivity(intent);
                    }

                }
        );
    }

    @Override
    public void Message(String msg) {

    }
}
