package com.example.exe_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

class SqlLiteDbHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/" + BuildConfig.APPLICATION_ID + "/databases/";
    private static String DB_NAME = "exe_2.db";
    private static final int DATABASE_VERSION = 1;
    private Context context = null;
    private static final String TABLE_NM = "user";
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PWD = "pwd";

    public SqlLiteDbHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        context = context;
        checkDataBase(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void checkDataBase(Context context) {
        String myPath = DB_PATH + DB_NAME;
        File dbfile = new File(myPath);
        if (dbfile.exists()) {
            SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
            db.close();
        } else {
            copyDataBase(context);
            checkDataBase(context);
        }
    }

    public void copyDataBase(Context context) {
        try {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
            InputStream myInput = context.getApplicationContext().getAssets().open(DB_NAME);
            String outFileName = DB_PATH + DB_NAME;
            File file = new File(outFileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException ea) {
            ea.printStackTrace();
        }
    }

    public void adduserdetail(Usr usr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, usr.getUsername());
        values.put(KEY_EMAIL, usr.getEmail());
        values.put(KEY_PWD, usr.getPwd());
        try {
            db.insert(TABLE_NM, null, values);
            Toast.makeText(context, "insert", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.d("chk", e.getMessage());
        }
        db.close();
    }

    public Usr usrdetail(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NM, new String[]{KEY_ID, KEY_USERNAME,
                        KEY_EMAIL, KEY_PWD}, KEY_EMAIL + "=?",
                new String[]{name}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Usr cont = new Usr();
        cont.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
        cont.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
        cont.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
        cont.setPwd(cursor.getString(cursor.getColumnIndex(KEY_PWD)));
        cursor.close();
        db.close();
        return cont;
    }
    public List<Usr> getAllContacts() {
        List<Usr> contactList = new ArrayList<Usr>();

        String selectQuery = "SELECT  * FROM " + TABLE_NM;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Usr usr = new Usr();
                usr.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                usr.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
                usr.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
                usr.setPwd(cursor.getString(cursor.getColumnIndex(KEY_PWD)));

                contactList.add(usr);
            } while (cursor.moveToNext());
        }
        return contactList;
    }
    public int updateuser(Usr usr) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, usr.getUsername());
        values.put(KEY_EMAIL, usr.getEmail());
        values.put(KEY_PWD, usr.getPwd());
        return db.update(TABLE_NM, values, KEY_ID + " = ?",
                new String[] { String.valueOf(usr.getId()) });
    }
}