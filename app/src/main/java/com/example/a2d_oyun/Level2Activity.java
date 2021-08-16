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

public class Level2Activity extends AppCompatActivity {


    private ConstraintLayout cl;
    private TextView textViewanlikskor;
    private TextView textViewLevel2Basla;
    private ImageView kirmiziucgen;
    private ImageView ork_odun;
    private ImageView siyahkare;
    private ImageView ana;
    private ImageView mermi;
    private Button ates_button;
    private ImageView ork_l;


    //pozisyonlar
    private int anakarakterx;
    private int anakaraktery;
    private int ork_odun_x;
    private int ork_odun_y;
    private int kirmiziucgen_x;
    private int kirmiziucgen_y;
    private int siyahkare_x;
    private int siyahkare_y;
    private int mermi_x;
    private int mermi_y;
    private int ork_l_x;
    private int ork_l_y;




    //boyutlar
    private int ekrangenisligi;
    private int ekranyuksekligi;
    private int anakarakteryukseklik;
    private int anakaraktergenislik;
    private int mermi_genislik;
    private int ork_odun_yukseklik;
    private int ork_l_yukseklik;


    //hızlar

    private int ana_hiz;
    private int ork_odun_hiz;
    private int siyahkare_hiz;
    private int kirmiziucgen_hiz;
    private int mermi_hiz;
    private int ork_l_hiz;

    //kontroller

    private boolean dokunmakontrol=false;
    private boolean baslangıckontrol=false;



    private int i=0;
    private int skor=0;

    private Timer timer=new Timer(); //timer 1 kereçalıştımı hep çalışır
    private Timer timer1=new Timer();
    private Handler handler=new Handler();
    private Handler handler1=new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2);

        cl=findViewById(R.id.cl);
        textViewanlikskor=findViewById(R.id.textViewanlikskor);
        textViewLevel2Basla=findViewById(R.id.textViewLevel2Basla);
        kirmiziucgen=findViewById(R.id.ork_kılıc);
        ork_odun=findViewById(R.id.ork_odun);
        siyahkare=findViewById(R.id.siyahkare);
        ana=findViewById(R.id.ana);
        ork_l=findViewById(R.id.ork_l);
        mermi=findViewById(R.id.mermi);
        ates_button=findViewById(R.id.ates_button);

        //cisimleri ekran dışına çıkarma
        siyahkare.setX(-80);
        siyahkare.setY(-80);
        ork_odun.setX(-150);
        ork_odun.setY(-150);
        kirmiziucgen.setX(-150);
        kirmiziucgen.setY(-150);
        mermi.setX(2000);
        mermi.setY(2000);
        ork_l.setX(-400);
        ork_l.setY(-400);





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

                    textViewLevel2Basla.setVisibility(View.INVISIBLE); //tıklayınca yok olsun

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
                    },0,20);  //runmetodu 20 sn aralıklarla çalışıcak
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
        mermi_hiz=Math.round(ekrangenisligi/40);
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
                                    mermi_y = anakaraktery+15;

                                }
                                mermi.setX(mermi_x);
                                mermi.setY(mermi_y);


                            }
                        });
                    }
                },0,20);

            }
        });










    }

    public void cisim_hareket(){

        ork_odun_hiz=Math.round(ekrangenisligi/140); //760/60=13
        siyahkare_hiz=Math.round(ekrangenisligi/60); //1280/30=20 fakrlı ekranlardaki hızları
        kirmiziucgen_hiz=Math.round(ekrangenisligi/40);
        ork_l_hiz=Math.round(ekrangenisligi/300);
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

        kirmiziucgen_x-=kirmiziucgen_hiz;
        if(kirmiziucgen_x<0){
            kirmiziucgen_x=ekrangenisligi+20;
            kirmiziucgen_y=(int)Math.floor(Math.random()*ekranyuksekligi);

        }
        kirmiziucgen.setX(kirmiziucgen_x);
        kirmiziucgen.setY(kirmiziucgen_y);

        ork_l_x-=ork_l_hiz;
        if(ork_l_x<0){
            ork_l_x=ekrangenisligi+20;
            ork_l_y=(int)Math.floor(Math.random()*ekranyuksekligi);

        }
        ork_l.setX(ork_l_x);
        ork_l.setY(ork_l_y);
    }

    public void carpisma(){
        //SARI İÇİN
        int saridairemerkez_x=ork_odun_x+ork_odun.getWidth()/2;
        int saridairemerkez_y=ork_odun_y+ork_odun.getHeight()/2;

        if(0 <= saridairemerkez_x && saridairemerkez_x <= anakaraktergenislik && anakaraktery <= saridairemerkez_y
                && saridairemerkez_y <= anakaraktery+anakarakteryukseklik ){

            skor+=20;
            ork_odun_x=-10;
            Log.e("sarı","geldi");

        }
        //KIRMIZI İÇİN
        int kirmiziucgenmerkez_x =kirmiziucgen_x+kirmiziucgen.getWidth()/2;
        int kirmiziucgenmerkez_y=kirmiziucgen_y+kirmiziucgen.getHeight()/2;

        if(0 <= kirmiziucgenmerkez_x && kirmiziucgenmerkez_x <= anakaraktergenislik && anakaraktery <= kirmiziucgenmerkez_y
                && kirmiziucgenmerkez_y <= anakaraktery+anakarakteryukseklik ){

            skor+=50;
            kirmiziucgen_x=-10;
            Log.e("kırmızı","geldi");

        }
        //MERMİ ÇARPIŞMASI
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

        //BÜYÜK ORK İÇİN

        int orkmerkez_x=ork_l_x+ork_l.getWidth()/2;
        int orkmerkez_y=ork_l_y+ork_l.getHeight()/2;

        if(0 <= orkmerkez_x && orkmerkez_x <= anakaraktergenislik && anakaraktery <= orkmerkez_y
                && orkmerkez_y <= anakaraktery+anakarakteryukseklik){

            orkmerkez_x = -10;

        }




        //SİYAH İÇİN
        int siyahkaremerkez_x=siyahkare_x + siyahkare.getWidth()/2;
        int siyahkaremerkez_y=siyahkare_y+siyahkare.getHeight()/2;





        if(0 <= siyahkaremerkez_x && siyahkaremerkez_x <= anakaraktergenislik && anakaraktery <= siyahkaremerkez_y
                && siyahkaremerkez_y <= anakaraktery+anakarakteryukseklik ){

            siyahkare_x=-10;
            Log.e("siyah","geldi");
            //time durdur
            timer.cancel();
            timer=null;

            Intent intent=new Intent(Level2Activity.this,SonucEkraniActivity.class);
            intent.putExtra("skor",skor);
            startActivity(intent);

        }

        textViewanlikskor.setText(String.valueOf(skor));



    }

}
