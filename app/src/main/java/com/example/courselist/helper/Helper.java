package com.example.courselist.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Helper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "course";

    public Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TABLE = "CREATE TABLE student (nim INTEGER PRIMARY KEY, name TEXT NOT NULL, email TEXT NOT NULL, phone TEXT NOT NULL);";
        final String SQL_CREATE_TABLE2 = "CREATE TABLE subject (subject_code INTEGER PRIMARY KEY, subject_name TEXT NOT NULL, subject_credit INTEGER NOT NULL);";
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS student");
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS subject");
        onCreate(sqLiteDatabase);
    }

    public ArrayList<HashMap<String, String>> getAll() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String QUERY = "SELECT * FROM student";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("nim", cursor.getString(0));
                map.put("name", cursor.getString(1));
                map.put("email", cursor.getString(2));
                map.put("phone", cursor.getString(3));
                list.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public ArrayList<HashMap<String, String>> getAll2() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String QUERY = "SELECT * FROM subject";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("subject_code", cursor.getString(0));
                map.put("subject_name", cursor.getString(1));
                map.put("subject_credit", cursor.getString(2));
                list.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void insert(int nim, String name, String email, String phone) {
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "INSERT INTO student (nim, name, email, phone) VALUES ("+ nim +", '"+ name +"', '"+ email +"', '"+ phone +"')";
        database.execSQL(QUERY);
    }

    public void insert2(int subject_code, String subject_name, int subject_credit) {
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "INSERT INTO subject (subject_code, subject_name, subject_credit) VALUES ("+ subject_code +", '"+ subject_name +"', "+ subject_credit +")";
        database.execSQL(QUERY);
    }

    public void update(int nim, String name, String email, String phone)  {
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "UPDATE student SET nim = "+ nim +", name = '"+ name +"', email = '"+ email +"', phone = '"+ phone +"' WHERE nim = "+ nim;
        database.execSQL(QUERY);
    }

    public void update2(int subject_code, String subject_name, int subject_credit)  {
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "UPDATE subject SET subject_code = "+ subject_code +", subject_name = '"+ subject_name +"', subject_credit = "+ subject_credit +" WHERE subject_code = "+ subject_code;
        database.execSQL(QUERY);
    }

    public void delete(int nim) {
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "DELETE FROM student WHERE nim = "+ nim;
        database.execSQL(QUERY);
    }

    public void delete2(int subject_code) {
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "DELETE FROM subject WHERE subject_code = "+ subject_code;
        database.execSQL(QUERY);
    }

    public String count(){
        SQLiteDatabase db = this.getWritableDatabase();
        String count = "SELECT count(*) FROM Subject";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        String icount = String.valueOf(mcursor.getInt(0));
        return icount;
    }

    public String count2(){
        SQLiteDatabase db = this.getWritableDatabase();
        String count = "SELECT count(*) FROM Student";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        String icount = String.valueOf(mcursor.getInt(0));
        return icount;
    }

}
