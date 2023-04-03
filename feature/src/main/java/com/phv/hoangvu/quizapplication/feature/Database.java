package com.phv.hoangvu.quizapplication.feature;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "User_Info.db";
    public static final String TABLE_NAME = "User_Info_table";
    public static final String COL_1 = "User_Name";
    public static final String COL_2 = "Pass_Word";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+COL_1+" TEXT PRIMARY KEY, "+COL_2+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    //Dang ky user
    public  Boolean addData(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Boolean result = true;

        ContentValues cv = new ContentValues();
        cv.put(COL_1, username);
        cv.put(COL_2, password);
        long r = db.insert(TABLE_NAME, null, cv);
        if(r == -1){
            result = false;
        }
        return result;
    }
    public Boolean findData(String userfind, String passfind)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String Sqlstr = "SELECT * FROM "+TABLE_NAME;
        Cursor cs = db.rawQuery(Sqlstr,null);
        if(cs.moveToFirst())
        {
            do{
                String userincursor = cs.getString(cs.getColumnIndex(COL_1));
                if (userfind.equals(userincursor))
                {
                    String passincorsor = cs.getString(cs.getColumnIndex(COL_2));
                    if(passfind.equals(passincorsor))
                    {
                        return true;
                    }else
                        {
                            return false;
                        }
                }
            }while(cs.moveToNext());
        }
        return false;
    }
    public boolean updateData(String nameupdate, String passupdate)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2,passupdate);
        nameupdate = "'"+nameupdate+"'";
        String SQLStr = COL_1+"="+nameupdate;
        long r = db.update(TABLE_NAME, cv,SQLStr,null);
        if(r==-1){return false;}
        return true;
    }
    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }
    public ArrayList<User> readData(){
        ArrayList<User> listUser = new ArrayList<>();
        String Sqlstr = "SELECT * FROM " +TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(Sqlstr, null);
        if(cursor.moveToFirst()){
            do{
                User user= new User();
                user.setUsername(cursor.getString(0)+"");
                user.setPassword(cursor.getString(1));
                listUser.add(user);
            } while (cursor.moveToNext());
        }
        db.close();
        return listUser;
    }
    public int deleteUser(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,COL_1+"=?",new String[] {String.valueOf(name)});
    }
}
