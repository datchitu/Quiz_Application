package com.phv.hoangvu.quizapplication.feature;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class Th5 extends AppCompatActivity {

    ConstraintLayout screen;
    ArrayList<Integer> arrayscreen;
    ImageView imaV;
    Button btn1,btn2,btn3,btn4;

    CountDownTimer countDownTimer;
    TextView tvtime,tvdiem;
    private static final long starTimeinmillis = 30000;
    private long timeLeftInMilliseconds =starTimeinmillis;

    int pointaltp;
    String userStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_th5);

        screen = (ConstraintLayout) findViewById(R.id.backgroundth5);

        arrayscreen = new ArrayList<>();
        arrayscreen.add(R.drawable.images1);
        arrayscreen.add(R.drawable.images2);
        arrayscreen.add(R.drawable.images3);

        Random random = new Random();
        int locaton = random.nextInt(arrayscreen.size());

        screen.setBackgroundResource(arrayscreen.get(locaton));

        imaV = (ImageView) findViewById(R.id.imageview);

        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);

        btselect1();

        tvtime = (TextView) findViewById(R.id.tvTime);
        tvdiem = (TextView) findViewById(R.id.tvDiem);
        Pointaltp();
        tvdiem.setText(pointaltp + "");
        timecd();
        UserStr();
    }

    private void btselect1(){
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                Toast.makeText(Th5.this,"Bạn đã trả lời chính xác!",Toast.LENGTH_SHORT).show();
                pointaltp += 5;
                tvdiem.setText(pointaltp + "");
                Intent intent = new Intent(Th5.this,Cgtlt.class);
                intent.putExtra("Point_altp",pointaltp);
                intent.putExtra("user_Str",userStr);
                startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                Toast.makeText(Th5.this,"Câu trả lời của bạn không chính xác !",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Th5.this,Sumaltp.class);
                intent.putExtra("Point_altp",pointaltp);
                intent.putExtra("user_Str",userStr);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                Toast.makeText(Th5.this,"Câu trả lời của bạn không chính xác !",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Th5.this,Sumaltp.class);
                intent.putExtra("Point_altp",pointaltp);
                intent.putExtra("user_Str",userStr);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                Toast.makeText(Th5.this,"Câu trả lời của bạn không chính xác !",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Th5.this,Sumaltp.class);
                intent.putExtra("Point_altp",pointaltp);
                intent.putExtra("user_Str",userStr);
                startActivity(intent);
            }
        });
    }

    public void timecd()
    {
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliseconds =millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                Toast.makeText(Th5.this,"Hết thời gian !",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Th5.this,Sumaltp.class);
                intent.putExtra("Point_altp",pointaltp);
                intent.putExtra("user_Str",userStr);
                startActivity(intent);
            }
        }.start();
    }
    public void updateTimer(){
        int minutes = (int) (timeLeftInMilliseconds / 1000) / 60;
        int seconds = (int) (timeLeftInMilliseconds / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        tvtime.setText(timeLeftFormatted);
    }
    public  void Pointaltp()
    {
        Bundle db = getIntent().getExtras();
        pointaltp = db.getInt("Point_altp");
    }
    private void UserStr()
    {
        Bundle db = getIntent().getExtras();
        userStr = db.getString("user_Str");
    }
}
