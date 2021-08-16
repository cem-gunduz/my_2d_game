package com.example.a2d_oyun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SonucEkraniActivity extends AppCompatActivity {

    private TextView textViewToplam;
    private TextView textViewEnYüksekSkor;
    private Button buttonTekrarBasla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc_ekrani);

        textViewToplam=findViewById(R.id.textViewToplam);
        textViewEnYüksekSkor=findViewById(R.id.textViewEnYüksekSkor);
        buttonTekrarBasla=findViewById(R.id.buttonTekrarBasla);

        int skor=getIntent().getIntExtra("skor",0);
        textViewToplam.setText(String.valueOf(skor));

        SharedPreferences sp=getSharedPreferences("Sonuc", Context.MODE_PRIVATE);
        int enyuksekskor=sp.getInt("enyuksekskor",0);

        if(skor > enyuksekskor){
            SharedPreferences.Editor editor=sp.edit();
            editor.putInt("enyuksekskor",skor);
            editor.commit();

            textViewEnYüksekSkor.setText(String.valueOf(skor));
        }
        else {
            textViewEnYüksekSkor.setText(String.valueOf(enyuksekskor));
        }


        buttonTekrarBasla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SonucEkraniActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}
