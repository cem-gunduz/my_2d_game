
package com.example.a2d_oyun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class OyunEkraniActivity extends AppCompatActivity{

    private ConstraintLayout cl;
    private TextView textViewanlikskor;
    private TextView textViewOyunaBasla;
    private ImageView ork_kılıc;
    private ImageView ork_odun;
    private ImageView siyahkare;
    public ImageView ana;
    private ImageView mermi;
    private Button ates_button;
    private ImageView ork_balta;
    private ImageView mermi_dusman;


    //pozisyonlar
    private int anakarakterx;
    public int anakaraktery;
    private int ork_odun_x;
    private int ork_odun_y;
    private int ork_kılıc_x;
    private int ork_kılıc_y;
    private int siyahkare_x;
    private int siyahkare_y;
    private int mermi_x;
    private int mermi_y;
    private int mermi_dusman_x;
    private int mermi_dusman_y;
    private int ork_balta_x;
    private int ork_balta_y;




    //boyutlar
    private int ekrangenisligi;
    public int ekranyuksekligi;
    public int anakarakteryukseklik;
    private int anakaraktergenislik;
    private int mermi_genislik;
    private int ork_odun_yukseklik;
    private int ork_kılıc_yukseklik;
    private int ork_balta_yukseklik;
    private int ork_balta_genislik;
    private int mermi_dusman_genislik;
    private int mermi_dusman_yukseklik;


    //hızlar

    public int ana_hiz;
    private int ork_odun_hiz;
    private int siyahkare_hiz;
    private int ork_kılıc_hiz;
    private int mermi_hiz;
    private int mermi_dusman_hiz;
    private int ork_balta_hiz;

    //kontroller

    public boolean dokunmakontrol=false;
    private boolean baslangıckontrol=false;




    private int skor=0;
    private int i=0;
    private int b=0;
    private int a=0;

    private Timer timer=new Timer(); //timer 1 kereçalıştımı hep çalışır
    private Timer timer1=new Timer();
    private Handler handler=new Handler();
    private Handler handler1=new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun_ekrani);




        cl=findViewById(R.id.cl);
        textViewanlikskor=findViewById(R.id.textViewanlikskor);
        textViewOyunaBasla=findViewById(R.id.textViewOyunaBasla);
        ork_kılıc=findViewById(R.id.ork_kılıc);
        ork_odun=findViewById(R.id.ork_odun);
        siyahkare=findViewById(R.id.siyahkare);
        ana=findViewById(R.id.ana);
        mermi=findViewById(R.id.mermi);
        ates_button=findViewById(R.id.ates_button);
        ork_balta=findViewById(R.id.ork_balta);
        mermi_dusman=findViewById(R.id.mermi_dusman);

        //cisimleri ekran dışına çıkarma
        siyahkare.setX(-80);
        siyahkare.setY(-80);
        ork_odun.setX(-150);
        ork_odun.setY(-150);
        ork_kılıc.setX(-150);
        ork_kılıc.setY(-150);
        mermi.setX(2000);
        mermi.setY(2000);
        mermi_dusman.setX(-100);
        mermi_dusman.setY(-100);
        ork_balta.setY(-1000);
        ork_balta.setX(-1000);








        cl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(baslangıckontrol){
                    if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        Log.e("MotionEvent","Ekrana dokunuldu");
                        dokunmakontrol=true;
                    }
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        Log.e("MotionEvent","Ekranı Bıraktı");
                        dokunmakontrol=false;
                    }

                }
                else {
                    baslangıckontrol=true;

                    textViewOyunaBasla.setVisibility(View.INVISIBLE); //tıklayınca yok olsun

                    anakarakterx=(int) ana.getX();
                    anakaraktery=(int)ana.getY();

                    anakaraktergenislik=(int)ana.getWidth();
                    anakarakteryukseklik=(int)ana.getHeight();
                    ekrangenisligi=cl.getWidth();
                    ekranyuksekligi=cl.getHeight();

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    anakarakter_hareket();
                                    mermi_ates();

                                    cisim_hareket();
                                    carpisma();


                                }
                            });
                        }
                    },0,20);  //runmetodu 20 milisn aralıklarla çalışıcak
                }




                return true;
            }
        });




    }


        public void anakarakter_hareket(){

        ana_hiz=Math.round(ekranyuksekligi/60);

        if(dokunmakontrol){
            anakaraktery-=ana_hiz;

        }else{
            anakaraktery+=ana_hiz;

        }
        if(anakaraktery<=0){
            anakaraktery=0;
        }
        if (anakaraktery>= ekranyuksekligi-anakarakteryukseklik){
            anakaraktery=ekranyuksekligi-anakarakteryukseklik;
        }




        ana.setY(anakaraktery);
    }


    public void mermi_ates(){
        mermi_hiz=Math.round(ekrangenisligi/20);
        mermi_x+=mermi_hiz;

        ates_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer1.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler1.post(new Runnable() {
                            @Override
                            public void run() {
                                if (mermi_x > ekrangenisligi) {
                                    mermi_x = anakarakterx;
                                    mermi_y = anakaraktery - 2;

                                }
                                mermi.setX(mermi_x);
                                mermi.setY(mermi_y);

                                dusman_mermi();
                            }


                        });

                    }
                },0,20);

            }
        });

    }


    public void dusman_mermi(){
        mermi_dusman_hiz=Math.round(ekrangenisligi/150);
        mermi_dusman_x-=mermi_dusman_hiz;


        if ( skor >20 && mermi_dusman_x < 0) {
            mermi_dusman_x = ork_balta_x;
            mermi_dusman_y = ork_balta_y+90;


        }
        mermi_dusman.setX(mermi_dusman_x);
        mermi_dusman.setY(mermi_dusman_y);


    }


    public void cisim_hareket(){
        //HIZLAR
        ork_odun_hiz=Math.round(ekrangenisligi/180);                               //760/60=13
        siyahkare_hiz=Math.round(ekrangenisligi/200);                               //1280/30=20 fakrlı ekranlardaki hızları
        ork_kılıc_hiz=Math.round(ekrangenisligi/200);
        ork_balta_hiz=Math.round(ekrangenisligi/180);

        siyahkare_x-=siyahkare_hiz;
        if(siyahkare_x<0){
            siyahkare_x=ekrangenisligi+20;
            siyahkare_y=(int)Math.floor(Math.random()*ekranyuksekligi);

        }
        siyahkare.setX(siyahkare_x);
        siyahkare.setY(siyahkare_y);


        ork_odun_x-=ork_odun_hiz;
        if(ork_odun_x<0){
            ork_odun_x=ekrangenisligi+20;
            ork_odun_y=(int)Math.floor(Math.random()*ekranyuksekligi);

        }
        ork_odun.setX(ork_odun_x);
        ork_odun.setY(ork_odun_y);

        ork_kılıc_x-=ork_kılıc_hiz;
        if(ork_kılıc_x<0){
            ork_kılıc_x=ekrangenisligi+20;
            ork_kılıc_y=(int)Math.floor(Math.random()*ekranyuksekligi);

        }
        ork_kılıc.setX(ork_kılıc_x);
        ork_kılıc.setY(ork_kılıc_y);


        //nsdfndfndfn

        if(skor>20){
            dusman_mermi();
            //ork_odun.setImageBitmap(null);
            // ork_kılıc.setImageBitmap(null);

            ork_balta_yukseklik=ork_balta.getHeight();
            ork_balta_genislik=ork_balta.getWidth();
            ork_balta_y-=ork_balta_hiz;
            ork_balta_x=ekrangenisligi-ork_balta_genislik; //x noktası

            if(ork_balta_y<=0){
                ork_balta_y=(int)Math.floor(Math.random()*ekranyuksekligi); //başlama y si

            }
            ork_balta.setX(ork_balta_x);
            ork_balta.setY(ork_balta_y);

        }
    }
    public void carpisma(){
        int ork_odun_merkez_x=ork_odun_x+ork_odun.getWidth()/2;
        int ork_odun_merkez_y=ork_odun_y+ork_odun.getHeight()/2;

        if(0 <= ork_odun_merkez_x && ork_odun_merkez_x <= anakaraktergenislik && anakaraktery <= ork_odun_merkez_y
                && ork_odun_merkez_y <= anakaraktery+anakarakteryukseklik ){

            ork_odun_x=-10;
            Log.e("siyah","geldi");
            //time durdur
            timer.cancel();
            timer=null;

            Intent intent=new Intent(OyunEkraniActivity.this,SonucEkraniActivity.class);
            intent.putExtra("skor",skor);
            finish();
            startActivity(intent);


        }

        int ork_kılıc_merkez_x =ork_kılıc_x+ork_kılıc.getWidth()/2;
        int ork_kılıc_merkez_y=ork_kılıc_y+ork_kılıc.getHeight()/2;

        if(0 <= ork_kılıc_merkez_x && ork_kılıc_merkez_x <= anakaraktergenislik && anakaraktery <= ork_kılıc_merkez_y
                && ork_kılıc_merkez_y <= anakaraktery+anakarakteryukseklik ){

            ork_kılıc_x=-10;
            Log.e("siyah","geldi");
            //time durdur
            timer.cancel();
            timer=null;

            Intent intent=new Intent(OyunEkraniActivity.this,SonucEkraniActivity.class);
            intent.putExtra("skor",skor);
            finish();
            startActivity(intent);


        }

        //MERMİ ÇARPIŞMASI ORK_ODUN
        int mermi_merkez_x=mermi_x+mermi.getWidth()/2;
        int mermi_merkez_y=mermi_y+mermi.getHeight()/2;
        mermi_genislik=(int)mermi.getWidth();
        ork_odun_yukseklik=ork_odun.getHeight();

        if(mermi_merkez_x >= ork_odun_x && mermi_merkez_y >= ork_odun_y && mermi_merkez_y <= ork_odun_y+ork_odun_yukseklik){
            i=i+1;
            mermi_x = 2000;
            if(i==2) {
                skor += 20;

                ork_odun_x = -10;
                i=0;
                Log.e("mermi", "carptı");
            }

        }
        //MERMİ ÇARPIŞMASI ORK_KILLC

        ork_kılıc_yukseklik=ork_odun.getHeight();

        if(mermi_merkez_x >= ork_kılıc_x && mermi_merkez_y >= ork_kılıc_y && mermi_merkez_y <= ork_kılıc_y+ork_kılıc_yukseklik){
            i=i+1;
            mermi_x = 2000;
            if(i==3) {
                skor += 40;

                ork_kılıc_x = -10;
                i=0;
                Log.e("mermi", "carptı");
            }

        }
        //MERMİ ÇARPMASI ORK_BALTA

        if(mermi_merkez_x >= ork_balta_x && mermi_merkez_y >= ork_balta_y && mermi_merkez_y <= ork_balta_y+ork_balta_yukseklik){
            b=b+1;
            mermi_x = 2000;
            if(b>6) {
                skor += 450;
                b=0;
                Log.e("mermi", "carptı");
                Intent intent=new Intent(OyunEkraniActivity.this,SonucEkraniActivity.class);
                intent.putExtra("skor",skor);
                finish();
                startActivity(intent);
            }

        }




        //DÜŞMAN MERMİ ÇARPMASI
        mermi_dusman_yukseklik=mermi_dusman.getHeight();
        if(mermi_dusman_x<=anakarakterx+anakaraktergenislik && mermi_dusman_y+mermi_dusman_yukseklik >= anakaraktery
                && mermi_dusman_y <= anakaraktery+anakarakteryukseklik){
            a=a+1;
            mermi_dusman_x=-80;
            if(a>2){
                a=0;
                timer.cancel();
                timer=null;



                Intent intent=new Intent(OyunEkraniActivity.this,SonucEkraniActivity.class);
                intent.putExtra("skor",skor);
                finish();
                startActivity(intent);

            }

        }

        int siyahkaremerkez_x=siyahkare_x + siyahkare.getWidth()/2;
        int siyahkaremerkez_y=siyahkare_y+siyahkare.getHeight()/2;

        if(0 <= siyahkaremerkez_x && siyahkaremerkez_x <= anakaraktergenislik && anakaraktery <= siyahkaremerkez_y
                && siyahkaremerkez_y <= anakaraktery+anakarakteryukseklik ){

            siyahkare_x=-10;
            Log.e("siyah","geldi");
            //time durdur
            timer.cancel();
            timer=null;

            Intent intent=new Intent(OyunEkraniActivity.this,SonucEkraniActivity.class);
            intent.putExtra("skor",skor);
            finish();
            startActivity(intent);


        }

        textViewanlikskor.setText(String.valueOf(skor));

    }






}
