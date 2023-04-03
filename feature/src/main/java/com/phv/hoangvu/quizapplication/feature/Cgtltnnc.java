package com.phv.hoangvu.quizapplication.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Cgtltnnc extends AppCompatActivity {

    Button bt;
    String userStr;
    ConstraintLayout screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgtltnnc);

        anhxa();
        screen.setBackgroundResource(R.drawable.nncsum);
        UserStr();
        replay();
    }
    private void anhxa()
    {
        bt = (Button) findViewById(R.id.replay);
        screen = (ConstraintLayout) findViewById(R.id.backgroundcgtltnnc);
    }

    private void replay()
    {
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cgtltnnc.this,Modcustom.class);
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
