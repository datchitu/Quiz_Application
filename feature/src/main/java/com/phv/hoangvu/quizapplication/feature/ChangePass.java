package com.phv.hoangvu.quizapplication.feature;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePass extends AppCompatActivity {

    EditText txtoldpass,txtrepass,txtnewpass;
    Button btdongy,bttrove;
    Database database;

    String userStr;
    String newpassStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        Anhxa();
        database = new Database(this);
        UserStr();
        Changepass();
    }

    private void Anhxa() {
        txtoldpass = (EditText) findViewById(R.id.oldpass);
        txtrepass = (EditText) findViewById(R.id.repass);
        txtnewpass = (EditText) findViewById(R.id.newpass);
        btdongy = (Button) findViewById(R.id.dongy);
        bttrove = (Button) findViewById(R.id.btTrove);
        database = new Database(this);
    }

    private void Changepass()
    {
        btdongy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String passStr = txtoldpass.getText().toString();
                newpassStr = txtnewpass.getText().toString();
                String repassStr = txtrepass.getText().toString();
                if(!newpassStr.isEmpty())
                {
                    if(!passStr.isEmpty())
                    {
                        if(newpassStr.equals(repassStr)){
                            Boolean Resuccess = database.findData(userStr, passStr);
                            if (Resuccess)
                            {
                                    AlertDialog.Builder dialogTrove = new AlertDialog.Builder(ChangePass.this);
                                    dialogTrove.setCancelable(false);
                                    dialogTrove.setMessage("Bạn có thay đổi mật khẩu không ?");
                                    dialogTrove.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Boolean Changesuccess = database.updateData(userStr, newpassStr);
                                            if(Changesuccess) {
                                                Toast.makeText(ChangePass.this, "Thay đổi mật khẩu thành công !", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(ChangePass.this, Modcustom.class);
                                                intent.putExtra("user_Str", userStr);
                                                startActivity(intent);
                                            } else {
                                                Toast.makeText(ChangePass.this,"Cập nhật mật khẩu không thành công!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                    dialogTrove.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    });
                                    dialogTrove.show();
                            }else{
                                Toast.makeText(ChangePass.this,"Mật khẩu cũ không đúng!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(ChangePass.this, "Mật khẩu nhập lại không trùng khớp !", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(ChangePass.this, "Bạn chưa nhập mật khẩu !", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ChangePass.this, "Bạn chưa nhập mật khẩu mới !", Toast.LENGTH_SHORT).show();
                }

            }
        });
        bttrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogTrove = new AlertDialog.Builder(ChangePass.this);
                dialogTrove.setCancelable(false);
                dialogTrove.setMessage("Bạn có muốn quay lại không ?");
                dialogTrove.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ChangePass.this,Modcustom.class);
                        intent.putExtra("user_Str",userStr);
                        startActivity(intent);
                    }
                });
                dialogTrove.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialogTrove.show();
            }
        });
    }
    private void UserStr()
    {
        Bundle db = getIntent().getExtras();
        userStr = db.getString("user_Str");
    }
}
