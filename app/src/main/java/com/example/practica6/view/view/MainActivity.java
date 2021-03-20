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
import com.sokah.parcial1.R;
import com.sokah.parcial1.communication.OnMessageListener;
import com.sokah.parcial1.communication.TCP_Singleton;
import com.sokah.parcial1.model.Name;
import com.sokah.parcial1.view.GameActivity;

public class MainActivity extends AppCompatActivity implements MessageListener {

    EditText inputName;
    Button buttoncontinue;
    TCP_Singleton tcp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tcp = TCP_Singleton.getInstance();
        tcp.SetObserver(this);
        inputName = findViewById(R.id.inputName);
        buttoncontinue = findViewById(R.id.buttonContinue);
        buttoncontinue.setOnClickListener(
                (v) -> {
                    if(inputName.getText().length()>0) {
                        Intent intent = new Intent(this, GameActivity.class);
                        Gson gson = new Gson();
                        Name name = new Name(inputName.getText().toString());
                        String message = gson.toJson(name);
                        tcp.SendMessage(message);
                        startActivity(intent);
                    }

                }
        );
    }

    @Override
    public void OnMessage(String msg) {
        Log.e("TAG", msg );
    }

    @Override
    public void Message(String msg) {

    }
}
