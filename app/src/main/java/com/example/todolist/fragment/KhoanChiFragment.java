package com.example.todolist.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.todolist.R;
import com.example.todolist.adapter.KhoanChi_ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class KhoanChiFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    View view;
    public KhoanChiFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_khoanchi, container, false);

        view =inflater.inflate(R.layout.fragment_khoanchi, container, false);

        viewPager=view.findViewById(R.id.viewpager_khoanchi);
        tabLayout = view.findViewById(R.id.tablayout_khoanchi);
        tabLayout.addTab(tabLayout.newTab().setText("KHOẢN CHI"));
        tabLayout.addTab(tabLayout.newTab().setText("LOẠI CHI"));

        KhoanChi_ViewPagerAdapter adapter = new KhoanChi_ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }
}