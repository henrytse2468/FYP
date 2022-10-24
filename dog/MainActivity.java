package com.example.dog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button reserve;
    private Button start;
    private Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reserve = findViewById(R.id.btn_reserve); 
        start = findViewById(R.id.btn_startToUse);
        reserve.setOnClickListener(this);
        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == reserve){
            intent = new Intent(this, reserve_page.class);
            startActivity(intent);
        }
        if (v == start){
            intent = new Intent(this, use_page.class);
            startActivity(intent);
        }
    }
}