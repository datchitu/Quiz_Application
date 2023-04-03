package com.phv.hoangvu.quizapplication.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Random;

public class Modcustom extends AppCompatActivity {

    ConstraintLayout screen;
    ArrayList<Integer> arrayscreen;
    ImageButton imbtnnc, imbtaltp;
    Button btchangepass,btlogout;
    String userStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modcustom);

        screen = (ConstraintLayout) findViewById(R.id.backgroundmod);

        arrayscreen = new ArrayList<>();
        arrayscreen.add(R.drawable.images1);
        arrayscreen.add(R.drawable.images2);
        arrayscreen.add(R.drawable.images3);

        Random random = new Random();
        int locaton = random.nextInt(arrayscreen.size());

        screen.setBackgroundResource(arrayscreen.get(locaton));
        anhxa();
        UserStr();
        SetBt();
        modcustom();
    }

    private void anhxa()
    {
        imbtaltp = (ImageButton) findViewById(R.id.altp);
        imbtnnc = (ImageButton) findViewById(R.id.nnc);
        btchangepass = (Button) findViewById(R.id.changepass);
        btlogout = (Button) findViewById(R.id.dangxuat);
    }

    private void modcustom()
    {
        imbtaltp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Modcustom.this,Themecustom.class);
                intent.putExtra("user_Str", userStr);
                startActivity(intent);
            }
        });

        imbtnnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Modcustom.this,Main2Activity.class);
                intent.putExtra("user_Str", userStr);
                startActivity(intent);
            }
        });

        btchangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!userStr.equals("admin")) {
                    Intent intent = new Intent(Modcustom.this, ChangePass.class);
                    intent.putExtra("user_Str", userStr);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(Modcustom.this, ListUser.class);
                    intent.putExtra("user_Str", userStr);
                    startActivity(intent);
                }
            }
        });

        btlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Modcustom.this,DangNhapActivity.class);
                startActivity(intent);
            }
        });
    }
    private void UserStr()
    {
        Bundle db = getIntent().getExtras();
        userStr = db.getString("user_Str");
    }
    private void SetBt()
    {
        if (!userStr.equals("admin"))
        {
            btchangepass.setText("Thay đổi mật khẩu");
        }else {
            btchangepass.setText("Danh sách người dùng");
        }
    }
}
