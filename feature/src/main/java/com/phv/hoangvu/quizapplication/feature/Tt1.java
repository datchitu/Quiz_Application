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

public class Tt1 extends AppCompatActivity {

    ConstraintLayout screen;
    ArrayList<Integer> arrayscreen;
    ImageView imaV;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;

    CountDownTimer countDownTimer;
    TextView tvtime,tvdiem;
    private static final long starTimeinmillis = 30000;
    private long timeLeftInMilliseconds =starTimeinmillis;

    int pointaltp = 0;
    String userStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tt1);

        screen = (ConstraintLayout) findViewById(R.id.backgroundtt1);

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
        tvdiem.setText(pointaltp + "");
        timecd();
        UserStr();
    }

    private void btselect1(){
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                Toast.makeText(Tt1.this,"Bạn đã trả lời chính xác!",Toast.LENGTH_SHORT).show();
                pointaltp += 5;
                tvdiem.setText(pointaltp + "");
                Intent intent = new Intent(Tt1.this,Tt2.class);
                intent.putExtra("Point_altp",pointaltp);
                intent.putExtra("user_Str",userStr);
                startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                Toast.makeText(Tt1.this,"Câu trả lời của bạn không chính xác !",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Tt1.this,Sumaltp.class);
                intent.putExtra("Point_altp",pointaltp);
                intent.putExtra("user_Str",userStr);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                Toast.makeText(Tt1.this,"Câu trả lời của bạn không chính xác !",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Tt1.this,Sumaltp.class);
                intent.putExtra("Point_altp",pointaltp);
                intent.putExtra("user_Str",userStr);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                Toast.makeText(Tt1.this,"Câu trả lời của bạn không chính xác !",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Tt1.this,Sumaltp.class);
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
                Toast.makeText(Tt1.this,"Hết thời gian !",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Tt1.this,Sumaltp.class);
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
    private void UserStr()
    {
        Bundle db = getIntent().getExtras();
        userStr = db.getString("user_Str");
    }
}
