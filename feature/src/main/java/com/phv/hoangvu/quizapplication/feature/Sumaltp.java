package com.phv.hoangvu.quizapplication.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Sumaltp extends AppCompatActivity {

    Button btcl;
    TextView tvdiem;
    int pointaltp;
    String userStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumaltp);

        anhxa1();
        Pointaltp();
        tvdiem.setText(pointaltp + "");
        UserStr();
        replay1();
    }
    private void anhxa1()
    {
        btcl = (Button) findViewById(R.id.buttoncl1);
        tvdiem = (TextView) findViewById(R.id.tvDiem);
    }
    private void replay1()
    {
        btcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sumaltp.this,Modcustom.class);
                intent.putExtra("user_Str", userStr);
                startActivity(intent);
            }
        });
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
