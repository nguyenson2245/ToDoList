package com.example.todolist.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBase extends SQLiteOpenHelper {
    public MyDataBase(Context context) {
        super(context, "QUANLYTHUCHI", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE THUCHI(" + "maKhoan integer PRIMARY KEY AUTOINCREMENT," + "tenKhoan text," + "loaiKhoan integer )";
        db.execSQL(sql);

        //Tạo bảng giao dịch, cho maGd tự tăng lên
        sql = "CREATE TABLE GIAODICH(" +
                "maGd integer PRIMARY KEY AUTOINCREMENT," +
                "moTaGd text," +
                "ngayGd date," +
                "soTien integer, " +
                "maKhoan integer REFERENCES thuchi(maKhoan))";
        db.execSQL(sql);

        sql = "CREATE TABLE taiKhoan(tenTaiKhoan text primary key, matKhau text)";
        db.execSQL(sql);
        sql = "INSERT INTO taiKhoan VALUES('admin','admin')";
        db.execSQL(sql);
        sql = "INSERT INTO taiKhoan VALUES('admin1','admin1')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS thuchi");
        db.execSQL("DROP TABLE IF EXISTS giaodich");
        onCreate(db);
    }
}
