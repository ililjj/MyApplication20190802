package com.example.myapplication20190802;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Start extends AppCompatActivity implements View.OnClickListener{

    //시작버튼
    Button button_Start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        button_Start = findViewById(R.id.Button_Start);
        button_Start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == button_Start)
        {
            Intent _intent = new Intent(Start.this,MainActivity.class);
            finish();
            startActivity(_intent);
        }

    }
}

