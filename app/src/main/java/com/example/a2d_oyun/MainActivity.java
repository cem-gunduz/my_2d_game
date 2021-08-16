package com.example.a2d_oyun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton buttonbasla;
    private Button nasil_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonbasla=findViewById(R.id.buttonbasla);
        nasil_button=findViewById(R.id.nasil_button);

        buttonbasla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,OyunEkraniActivity.class));
            }
        });

        nasil_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Opendialog();
            }
        });


    }
    public void Opendialog(){
        Dialog dialog=new Dialog();
        dialog.show(getSupportFragmentManager(),"dialog");
    }

}
