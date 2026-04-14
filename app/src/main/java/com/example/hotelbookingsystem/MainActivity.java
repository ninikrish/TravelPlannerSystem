package com.example.hotelbookingsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /*Start of code*/

        //Finds the button of Golden Retreat hotel and uses Intent to click to the Golden Retreat Activity
        Button btnGoldenRetreat = findViewById(R.id.btnGoldenRetreat);
        btnGoldenRetreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Golden_Retreat.class));
            }
        });

        //Finds the button of Royal Plaza hotel and uses Intent to click to the Royal Plaza Activity
        Button btnRoyalPlaza = findViewById(R.id.btnRoyalPlaza);
        btnRoyalPlaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RoyalPlaza.class));
            }
        });

        //Finds the button of Villa Resort hotel and uses Intent to click to the Villa Resort Activity
        Button btnVilla= findViewById(R.id.btnVilla);
        btnVilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Villa.class));
            }
        });


    }
}

