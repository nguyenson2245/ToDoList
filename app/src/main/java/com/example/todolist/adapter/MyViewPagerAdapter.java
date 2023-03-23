package com.example.todolist.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.todolist.fragment.DongLucFragment;
import com.example.todolist.fragment.HomeFragment;
import com.example.todolist.fragment.KhoanChiFragment;
import com.example.todolist.fragment.KhoanThuFragment;
import com.example.todolist.fragment.ThongKeFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {

    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new ThongKeFragment();
            case 2:
                return new KhoanThuFragment();
            case 3:
                return new KhoanChiFragment();
            case 4:
                return new DongLucFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
