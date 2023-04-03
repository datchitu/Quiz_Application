package com.phv.hoangvu.quizapplication.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Sumnnc extends AppCompatActivity {

    Button btreplay;
    TextView tvpoint;
    ConstraintLayout screen;
    int pointnnc;
    String userStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumnnc);
        anhxa();

        screen.setBackgroundResource(R.drawable.nncsum);

        Pointnnc();
        tvpoint.setText(pointnnc + "");
        UserStr();
        rePlay();
    }

    private void anhxa()
    {
        screen = (ConstraintLayout) findViewById(R.id.Sumnnc);
        btreplay = (Button) findViewById(R.id.btReplay);
        tvpoint = (TextView) findViewById(R.id.tvPoint);
    }

    private void rePlay()
    {
        btreplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sumnnc.this,Modcustom.class);
                intent.putExtra("user_Str",userStr);
                startActivity(intent);
            }
        });
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
