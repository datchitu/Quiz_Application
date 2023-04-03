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

public class dang_ky extends AppCompatActivity {

    EditText edituser,editpass,editrepass;
    Button btdangkyngay,bttrove;
    Database database;

    String usernamestr;
    String passwordstr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        Anhxa();
        Dangky();
    }

    private void Anhxa() {
        edituser = (EditText) findViewById(R.id.editUser1);
        editpass = (EditText) findViewById(R.id.editPass1);
        editrepass = (EditText) findViewById(R.id.editRepass);
        btdangkyngay = (Button) findViewById(R.id.btDangkyngay);
        bttrove = (Button) findViewById(R.id.btTrove);
        database = new Database(this);
    }
    private void Dangky(){
        btdangkyngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                usernamestr = edituser.getText().toString();
                passwordstr = editpass.getText().toString();
                String repassstr = editrepass.getText().toString();
                if(!usernamestr.isEmpty())
                {
                    if(!passwordstr.isEmpty())
                    {
                        if(passwordstr.equals(repassstr)){
                                AlertDialog.Builder dialogTrove = new AlertDialog.Builder(dang_ky.this);
                                dialogTrove.setCancelable(false);
                                dialogTrove.setMessage("Bạn có đăng ký không ?");
                                dialogTrove.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Boolean Regsuccess = database.addData(usernamestr, passwordstr);
                                        if(Regsuccess) {
                                            Toast.makeText(dang_ky.this, "Đăng ký thành công !", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(dang_ky.this, DangNhapActivity.class);
                                            startActivity(intent);
                                        }else {
                                            Toast.makeText(dang_ky.this, "Tên tài khoản đã tồn tại !", Toast.LENGTH_SHORT).show();
                                            edituser.setText("");
                                            editpass.setText("");
                                            editrepass.setText("");
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
                            Toast.makeText(dang_ky.this, "Mật khẩu nhập lại không trùng khớp !", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(dang_ky.this, "Bạn chưa nhập mật khẩu !", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(dang_ky.this, "Bạn chưa nhập tên tài khoản !", Toast.LENGTH_SHORT).show();
                }

            }
        });
        bttrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogTrove = new AlertDialog.Builder(dang_ky.this);
                dialogTrove.setCancelable(false);
                dialogTrove.setMessage("Bạn có muốn quay lại không ?");
                dialogTrove.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(dang_ky.this,DangNhapActivity.class);
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

}
