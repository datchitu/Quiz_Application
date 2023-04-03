package com.phv.hoangvu.quizapplication.feature;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class DangNhapActivity extends AppCompatActivity {

    EditText edituser,editpass;
    Button btdangnhap,btdangky;
    CheckBox cbluu;
    Database database;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_nhap);
        Anhxa();
        Dangky();
        Dangnhap();
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        edituser.setText(sharedPreferences.getString("userName",""));
        editpass.setText(sharedPreferences.getString("passWord",""));
        cbluu.setChecked(sharedPreferences.getBoolean("checKed", false));

    }

    private void Anhxa()
    {
        edituser = (EditText) findViewById(R.id.editUser);
        editpass = (EditText) findViewById(R.id.editPass);
        btdangnhap = (Button) findViewById(R.id.xacNhan);
        btdangky = (Button) findViewById(R.id.dangKy);
        cbluu = (CheckBox) findViewById(R.id.rmbPass);
        database = new Database(this);
    }

    private void Dangky()
    {
        btdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, dang_ky.class);
                startActivity(intent);
            }
        });
    }

    private void Dangnhap()
    {
        btdangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String usernamestr = edituser.getText().toString();
                String passwordstr = editpass.getText().toString();
                if (!usernamestr.isEmpty())
                {
                    if (!passwordstr.isEmpty())
                    {
                        if(!usernamestr.equals("admin") && !passwordstr.equals("5411")) {
                            Boolean LoginSuccess = database.findData(usernamestr, passwordstr);
                            if (LoginSuccess) {
                                Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                                if (cbluu.isChecked()) {
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("userName", usernamestr);
                                    editor.putString("passWord", passwordstr);
                                    editor.putBoolean("checKed", true);
                                    editor.commit();
                                } else {
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.remove("userName");
                                    editor.remove("passWord");
                                    editor.remove("checKed");
                                    editor.commit();
                                }
                                Intent intent = new Intent(DangNhapActivity.this, Modcustom.class);
                                intent.putExtra("user_Str", usernamestr);
                                startActivity(intent);

                            } else {
                                Toast.makeText(DangNhapActivity.this, "Tên tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(DangNhapActivity.this, "Đăng nhập admin thành công!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DangNhapActivity.this, Modcustom.class);
                            intent.putExtra("user_Str", usernamestr);
                            startActivity(intent);
                        }
                    }else
                        {
                            Toast.makeText(DangNhapActivity.this,"Bạn chưa nhập mật khẩu!", Toast.LENGTH_SHORT).show();
                        }
                }else
                    {
                        Toast.makeText(DangNhapActivity.this,"Bạn chưa nhập tên tài khoản!", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }
}
