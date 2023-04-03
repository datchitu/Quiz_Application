package com.phv.hoangvu.quizapplication.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListUser extends AppCompatActivity {

    Button bttrove,btupdate;
    ListView lvuser;
    String userStr;
    Database database;
    ArrayList<User> list;
    AdapterUser adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        database = new Database(this);
        Anhxa();
        list = database.readData();
        setAdapter();
        list.clear();
        list.addAll(database.readData());
        setAdapter();
        btUpdate();
        UserStr();
        btTrove();
    }
    private void Anhxa()
    {
        bttrove = (Button) findViewById(R.id.troVe);
        btupdate = (Button) findViewById(R.id.update);
        lvuser = (ListView) findViewById(R.id.listView);
    }
    private void UserStr()
    {
        Bundle db = getIntent().getExtras();
        userStr = db.getString("user_Str");
    }
    private void btTrove()
    {
        bttrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListUser.this,Modcustom.class);
                intent.putExtra("user_Str",userStr);
                startActivity(intent);
            }
        });
    }
    private void btUpdate(){
        btupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();;
            }
        });
    }
    private void setAdapter(){
        if (adapter == null){
            adapter = new AdapterUser(this,R.layout.listview_user,list);
            lvuser.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
            lvuser.setSelection(adapter.getCount()-1);
        }
    }
    private void updateUser(){
        list.clear();
        list.addAll(database.readData());
        setAdapter();
    }
}
