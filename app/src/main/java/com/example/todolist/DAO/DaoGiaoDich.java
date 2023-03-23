package com.example.todolist.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.todolist.DataBase.MyDataBase;
import com.example.todolist.model.GiaoDich;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DaoGiaoDich {
    MyDataBase mdb;
    SimpleDateFormat dfm = new SimpleDateFormat("dd/MM/yyyy");
    SQLiteDatabase dbGiaoDich;

    public DaoGiaoDich(Context context) {
        mdb = new MyDataBase(context);

    }

    public boolean themGD(GiaoDich gd) {
        dbGiaoDich = mdb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("moTaGd", gd.getMoTaGd());
        contentValues.put("ngayGd", dfm.format(gd.getNgayGd()));
        contentValues.put("soTien", gd.getSoTien());
        contentValues.put("maKhoan", gd.getMaKhoan());
        long r = dbGiaoDich.insert("GIAODICH", null, contentValues);
        if (r <= 0) {
            return false;
        }
        return true;
    }

    //Sửa giao dịch theo mã giao dịch
    public boolean suaGD(GiaoDich gd) {
        SQLiteDatabase db = mdb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("moTaGd", gd.getMoTaGd());
        contentValues.put("ngayGd", dfm.format(gd.getNgayGd()));
        contentValues.put("soTien", gd.getSoTien());
        contentValues.put("maKhoan", gd.getMaKhoan());
        int r = db.update("GIAODICH", contentValues, "maGd=?", new String[]{String.valueOf(gd.getMaGd())});
        if (r <= 0) {
            return false;
        }
        return true;
    }

    //Xóa giao dịch theo mã
    public boolean xoaGD(GiaoDich gd) {
        SQLiteDatabase db = mdb.getWritableDatabase();
        int r = db.delete("GIAODICH", "maGd=?", new String[]{String.valueOf(gd.getMaGd())});
        if (r <= 0) {
            return false;
        }
        return true;
    }

    public ArrayList<GiaoDich> getGD(String sql, String... a) {
        ArrayList<GiaoDich> list = new ArrayList<>();
        dbGiaoDich = mdb.getReadableDatabase();
        Cursor cs = dbGiaoDich.rawQuery(sql, a);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            try {
                int ma = Integer.parseInt(cs.getString(0));
                String mota = cs.getString(1);
                String ngay = cs.getString(2);
                int tien = Integer.parseInt(cs.getString(3));
                int maK = Integer.parseInt(cs.getString(4));
                GiaoDich gd = new GiaoDich(ma, mota, dfm.parse(ngay), tien, maK);
                list.add(gd);
                cs.moveToNext();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        cs.close();
        return list;
    }

    public ArrayList<GiaoDich> getAll() {
        String sql = "SELECT * FROM GIAODICH";
        return getGD(sql);
    }

    //Lấy giao dịch theo loại
    public ArrayList<GiaoDich> getGDtheoTC(int loaiKhoan) {
        String sql = "SELECT * FROM GIAODICH as gd INNER JOIN THUCHI as tc ON gd.maKhoan = tc.maKhoan WHERE tc.loaiKhoan=?";
        ArrayList<GiaoDich> list = getGD(sql, String.valueOf(loaiKhoan));
        return list;
    }
}

