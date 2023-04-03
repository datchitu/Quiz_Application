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

public class Nnc9 extends AppCompatActivity {

    ConstraintLayout screen;
    ArrayList<Integer> arrayscreen;
    ImageView imaV;
    Button btn1, btn2, btn3, btn4;
    CountDownTimer countDownTimer;
    TextView tvdiem;
    long starTimeinmillis = 20000;
    long timeLeftInMilliseconds =starTimeinmillis;

    int pointnnc;
    String userStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nnc9);

        screen = (ConstraintLayout) findViewById(R.id.background9);

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
        tvdiem = (TextView) findViewById(R.id.tvDiem);
        Pointnnc();
        tvdiem.setText(pointnnc + "");
        timecd();
        UserStr();
    }

    private void btselect1(){
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                Toast.makeText(Nnc9.this,"Bạn đã trả lời đúng tăng 1 điểm !",Toast.LENGTH_SHORT).show();
                pointnnc += 1;
                tvdiem.setText(pointnnc + "");
                if(pointnnc == 5) {
                    Intent intent = new Intent(Nnc9.this, Cgtltnnc.class);
                    intent.putExtra("Point_nnc", pointnnc);
                    intent.putExtra("user_Str",userStr);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(Nnc9.this, Nnc10.class);
                    intent.putExtra("Point_nnc", pointnnc);
                    intent.putExtra("user_Str",userStr);
                    startActivity(intent);
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                Toast.makeText(Nnc9.this,"Bạn đã trả lời sai hạ xuống 0 điểm !",Toast.LENGTH_SHORT).show();
                pointnnc = 0;
                tvdiem.setText(pointnnc + "");
                Intent intent = new Intent(Nnc9.this,Nnc10.class);
                intent.putExtra("Point_nnc",pointnnc);
                intent.putExtra("user_Str",userStr);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                Toast.makeText(Nnc9.this,"Bạn đã trả lời sai hạ xuống 0 điểm !",Toast.LENGTH_SHORT).show();
                pointnnc = 0;
                tvdiem.setText(pointnnc + "");
                Intent intent = new Intent(Nnc9.this,Nnc10.class);
                intent.putExtra("Point_nnc",pointnnc);
                intent.putExtra("user_Str",userStr);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                Toast.makeText(Nnc9.this,"Bạn đã trả lời sai hạ xuống 0 điểm !",Toast.LENGTH_SHORT).show();
                pointnnc = 0;
                tvdiem.setText(pointnnc + "");
                Intent intent = new Intent(Nnc9.this,Nnc10.class);
                intent.putExtra("Point_nnc",pointnnc);
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
                Toast.makeText(Nnc9.this,"Hết thời gian !",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Nnc9.this,Sumnnc.class);
                intent.putExtra("Point_nnc",pointnnc);
                intent.putExtra("user_Str",userStr);
                startActivity(intent);
            }
        }.start();
    }
    public void updateTimer(){
        int minutes = (int) (timeLeftInMilliseconds / 1000) / 60;
        int seconds = (int) (timeLeftInMilliseconds / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }
    public  void Pointnnc()
    {
        Bundle db = getIntent().getExtras();
        pointnnc = db.getInt("Point_nnc");
    }
    private void UserStr()
    {
        Bundle db = getIntent().getExtras();
        userStr = db.getString("user_Str");
    }
}
