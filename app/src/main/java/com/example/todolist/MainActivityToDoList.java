package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.DataBase.DataBase;
import com.example.todolist.adapter.CongViec_Adapter;
import com.example.todolist.model.CongViec;

import java.util.ArrayList;

public class MainActivityToDoList extends AppCompatActivity {

    TextView txt_home;
    Toolbar toolbar;
    DataBase dataBase;
    ListView lvCongViec;
    ArrayList<CongViec> array_CongViec;
    CongViec_Adapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_to_do_list);

        array_CongViec = new ArrayList<>();
        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        lvCongViec = findViewById(R.id.listView_cv);
        txt_home = findViewById(R.id.txt_home);

        txt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityToDoList.this, MainActivity.class));
            }
        });

        adapter = new CongViec_Adapter(MainActivityToDoList.this, R.layout.dong_cong_viec, array_CongViec);
        lvCongViec.setAdapter(adapter);
        dataBase = new DataBase(this, "GhiChu.sqlite", null, 1);
        dataBase.QueryData_("CREATE TABLE IF NOT EXISTS Bang_CongViec_Moi(Id INTEGER PRIMARY KEY AUTOINCREMENT , TenCV VARCHAR(200))");        // tạo 1 cái bảng nếu không tồn tại (nếu nó k trùng ),tên bảng =>  Bang_CongViec_Moi(ID LÀ SỐ NGUYÊN LÀM KHÓA CHÍNH TĂNG DẦN),TÊN CÔNG VIỆC  là chuỗi có chiều dài 200
        GetDataCongViec();
    }

    public void Dialog_xoa_CV(String tenCV, int id___CV) {
        AlertDialog.Builder dialog_xoa = new AlertDialog.Builder(this);
        dialog_xoa.setMessage("Bạn có ,muốn xóa công việc => " + tenCV + " <= không ?");

        dialog_xoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dataBase.QueryData_("DELETE FROM Bang_CongViec_Moi WHERE Id = '" + id___CV + "'");
                Toast.makeText(MainActivityToDoList.this, "Đã Xóa " + tenCV, Toast.LENGTH_SHORT).show();
                GetDataCongViec();
            }
        });

        dialog_xoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialog_xoa.show();
    }

    public void Dialog_Sua_CongViec(String ten_Cv, final int id) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_sua);

        EditText edt_TenCV = (EditText) dialog.findViewById(R.id.editText_chinhsua);
        Button btn_XacNhan = (Button) dialog.findViewById(R.id.button_capNhat__);
        Button btn_huy = (Button) dialog.findViewById(R.id.button_huy___);

        edt_TenCV.setText(ten_Cv);

        btn_XacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenMoi = edt_TenCV.getText().toString().trim();
                dataBase.QueryData_("UPDATE Bang_CongViec_Moi SET TenCV = '" + tenMoi + "' WHERE Id = '" + id + "'"); // chú ý điều kiện where
                Toast.makeText(MainActivityToDoList.this, "Đã Cập Nhật", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                GetDataCongViec();
                //Log.d("TAG", "onClick: "+ GetDataCongViec());// load lại công việc
            }
        });

        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void GetDataCongViec()  {
        Cursor dataCongViec = dataBase.GetData_("SELECT * FROM Bang_CongViec_Moi");
        array_CongViec.clear();
        while (dataCongViec.moveToNext()) {
            String ten = dataCongViec.getString(1);
            int id = dataCongViec.getInt(0);
            array_CongViec.add(new CongViec(id, ten));
            Log.d("TAG", "GetDataCongViec: }" + ten);
            Log.d("TAG", "GetDataCongViec: }" + id);
        }
        Log.d("TAG", "GetDataCongViec: }" + array_CongViec);
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_congviec_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == (R.id.menuAdd)) { // biến item id = id của cái item tạo trong menu
            Dialog_Them();
        }
        return super.onOptionsItemSelected(item);
    }

    private void Dialog_Them() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_them_cong_viec);

        // bắt sk => khai báo ax
        EditText edtTen = (EditText) dialog.findViewById(R.id.editText_them_cong_viec);
        Button btn_Them = (Button) dialog.findViewById(R.id.button_Them);
        Button btn_Huy = (Button) dialog.findViewById(R.id.button_Huy);

        btn_Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenCv = edtTen.getText().toString();
                if (tenCv.equals("")) {
                    Toast.makeText(MainActivityToDoList.this, "Vui Lòng Nhập tên công Việc", Toast.LENGTH_SHORT).show();
                } else {
                    dataBase.QueryData_("INSERT INTO Bang_CongViec_Moi VALUES(null,'" + tenCv + "')"); // tách chuỗi nối tiến
                    Toast.makeText(MainActivityToDoList.this, "Đã Thêm,", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    GetDataCongViec();
                }
            }
        });
        btn_Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();  // hủy tắt cái show ra
            }
        });

        dialog.show();
    }
}