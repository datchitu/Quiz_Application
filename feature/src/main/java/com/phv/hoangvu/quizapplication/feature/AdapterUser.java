package com.phv.hoangvu.quizapplication.feature;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AdapterUser extends ArrayAdapter<User>{
    private Context context;
    private int resoure;
    private List<User> listUser;
    private Database database;
    public AdapterUser(@NonNull Context context, @LayoutRes int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
        this.context= context;
        this.resoure= resource;
        this.listUser= objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        database = new Database(context);
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_user,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView)convertView.findViewById(R.id.txtUser);
            viewHolder.tvPass = (TextView)convertView.findViewById(R.id.txtPass);
            viewHolder.btRemove = (Button)convertView.findViewById(R.id.btRemove);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final User user = listUser.get(position);
        viewHolder.tvName.setText(user.getUsername());
        viewHolder.tvPass.setText(user.getPassword());
        viewHolder.btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogTrove = new AlertDialog.Builder(context);
                dialogTrove.setCancelable(false);
                dialogTrove.setMessage("Bạn có muốn xóa tài khoản không ?");
                dialogTrove.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int result = database.deleteUser(user.getUsername());
                        if(result > 0){
                            Toast.makeText(context,"Xoá tài khoản thành công!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Xóa tài khoản thất bại!", Toast.LENGTH_SHORT).show();
                        }
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
        return convertView;
    }

    public class ViewHolder{

        TextView tvName;
        TextView tvPass;
        Button btRemove;
    }
}
