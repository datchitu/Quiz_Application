package com.phv.hoangvu.quizapplication.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

public class Themecustom extends AppCompatActivity {

    ConstraintLayout screen;
    ArrayList<Integer> arrayscreen;
    Button bttt,btan,btvh,btdl,btth;
    String userStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themecustom);

        screen = (ConstraintLayout) findViewById(R.id.backgroundmodcustom);

        arrayscreen = new ArrayList<>();
        arrayscreen.add(R.drawable.images1);
        arrayscreen.add(R.drawable.images2);
        arrayscreen.add(R.drawable.images3);

        Random random = new Random();
        int locaton = random.nextInt(arrayscreen.size());

        screen.setBackgroundResource(arrayscreen.get(locaton));

        anhxa();
        UserStr();
        themecustom();
    }

    private void anhxa()
    {
        bttt = (Button) findViewById(R.id.lichsu);
        btan = (Button) findViewById(R.id.amnhac);
        btvh = (Button) findViewById(R.id.vanhoc);
        btdl = (Button) findViewById(R.id.toanhoc);
        btth = (Button) findViewById(R.id.tonghop);
    }

    private void themecustom()
    {
        bttt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Themecustom.this,Tt1.class);
                intent.putExtra("user_Str",userStr);
                startActivity(intent);
            }
        });

        btan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Themecustom.this,An1.class);
                intent.putExtra("user_Str",userStr);
                startActivity(intent);
            }
        });

        btvh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Themecustom.this,Vh1.class);
                intent.putExtra("user_Str",userStr);
                startActivity(intent);
            }
        });

        btdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Themecustom.this,Dl1.class);
                intent.putExtra("user_Str",userStr);
                startActivity(intent);
            }
        });
        btth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Themecustom.this,Th1.class);
                intent.putExtra("user_Str",userStr);
                startActivity(intent);
            }
        });
    }
    private void UserStr()
    {
        Bundle db = getIntent().getExtras();
        userStr = db.getString("user_Str");
    }
}
