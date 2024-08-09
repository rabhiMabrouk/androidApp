package com.example.login_java_sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databaseHelper extends SQLiteOpenHelper {
    public static final String databaseName = "signup.dp";

    public databaseHelper(@Nullable Context context) {
        super(context, "signup.dp", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        //تعمل هذه الدالة على تنفيذ أوامر SQL
        MyDatabase.execSQL("CREATE TABLE allusers(email TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists allusers");
    }
    public boolean insertData(String email,String password){
        SQLiteDatabase Mydatabase = this.getWritableDatabase();
        ContentValues contentValu = new ContentValues();
        contentValu.put("email",email);
        contentValu.put("password",password);
        //إذا كانت العملية ناجحة، فسيُرجع رقم الصف الذي تم إدخاله، وإذا فشلت، فسيُرجع -1.إذا كانت العملية ناجحة، فسيُرجع رقم الصف الذي تم إدخاله، وإذا فشلت، فسيُرجع -1.
        long result = Mydatabase.insert("allusers",null,contentValu);

        if (result ==-1)
            return false;
        return true;
    }
    public boolean checkemail(String email){
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("select * from allusers where email=?", new String[]{email});
        int count=cursor.getCount();
        cursor.close();
        if(count>0)
            return true;
        return false;
    }

    public boolean checkpassword(String email,String password){
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("select * from allusers where email=? and password=?",new String[]{email,password});
        int count=cursor.getCount();
        cursor.close();
        if(count>0)
            return true;
        return false;
    }
}
