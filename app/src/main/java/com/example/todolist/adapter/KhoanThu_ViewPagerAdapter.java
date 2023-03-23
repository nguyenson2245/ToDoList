package com.example.todolist.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.todolist.fragment.TabKhoanThuFragment;
import com.example.todolist.fragment.TabLoaiThuFragment;

public class KhoanThu_ViewPagerAdapter extends FragmentStatePagerAdapter {

    int numberTab = 2;

    public KhoanThu_ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TabKhoanThuFragment tab_khoanThu = new TabKhoanThuFragment();
                return tab_khoanThu;
            case 1:
                TabLoaiThuFragment tab_loaiThu = new TabLoaiThuFragment();
                return tab_loaiThu;
        }
        return null;
    }

    @Override
    public int getCount() {
        return numberTab;
    }
}
