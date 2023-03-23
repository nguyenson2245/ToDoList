package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.todolist.adapter.MyViewPagerAdapter;
import com.example.todolist.fragment.DongLucFragment;
import com.example.todolist.fragment.HomeFragment;
import com.example.todolist.fragment.KhoanChiFragment;
import com.example.todolist.fragment.KhoanThuFragment;
import com.example.todolist.fragment.ThongKeFragment;
import com.example.todolist.transformer.DepthPageTransformer;
import com.example.todolist.transformer.ZoomOutPageTransformer;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_ThONGKE = 1;
    private static final int FRAGMENT_KHOANTHU = 2;
    private static final int FRAGMENT_KHOANCHI = 3;
    private static final int FRAGMENT_DONGLUC = 4;

    private int mCurrentFragment = FRAGMENT_HOME;
    private BottomNavigationView bottomNavigationView;
    Toolbar toolbar;

    DrawerLayout drawerLayout;
    NavigationView navigationView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        setToolBar();

        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(this);

        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
        bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);


        //chuyển page
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.home) {
                    openHomeFragment();
                    navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                } else if (id == R.id.thongke) {
                    openThongKeFragment();
                    navigationView.getMenu().findItem(R.id.nav_thongke).setChecked(true);
                } else if (id == R.id.khoanthu) {
                    openKhoanThuFragment();
                    navigationView.getMenu().findItem(R.id.nav_khoanthu).setChecked(true);
                } else if (id == R.id.khoanchi) {
                    navigationView.getMenu().findItem(R.id.nav_khoanchi).setChecked(true);
                    openKhoanChiFragment();
                } else if (id == R.id.dongluc) {
                    openDongLucFragment();
                    navigationView.getMenu().findItem(R.id.nav_dongluc).setChecked(true);
                }
                return true;
            }
        });

    }
    private void setToolBar() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_navigationdr, R.string.close_navigationdr);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void AnhXa() {
        bottomNavigationView = findViewById(R.id.bottom_nav);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            openHomeFragment();
            bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);
        } else if (id == R.id.nav_thongke) {
            openThongKeFragment();
            bottomNavigationView.getMenu().findItem(R.id.thongke).setChecked(true);
        } else if (id == R.id.nav_khoanthu) {
            openKhoanThuFragment();
            bottomNavigationView.getMenu().findItem(R.id.khoanthu).setChecked(true);
        } else if (id == R.id.nav_khoanchi) {
            openKhoanChiFragment();
            bottomNavigationView.getMenu().findItem(R.id.khoanchi).setChecked(true);
        } else if (id == R.id.nav_dongluc) {
            openDongLucFragment();
            bottomNavigationView.getMenu().findItem(R.id.dongluc).setChecked(true);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openHomeFragment(){
        if(mCurrentFragment!= FRAGMENT_HOME){
            replaceFragment(new HomeFragment());
            mCurrentFragment = FRAGMENT_HOME;
        }
    }
    public void openThongKeFragment(){
        if(mCurrentFragment!=FRAGMENT_ThONGKE){
            replaceFragment(new ThongKeFragment());
            mCurrentFragment = FRAGMENT_ThONGKE;
        }
    }
    public void openKhoanThuFragment(){
        if(mCurrentFragment!=FRAGMENT_KHOANTHU){
            replaceFragment(new KhoanThuFragment());
            mCurrentFragment = FRAGMENT_KHOANTHU;
        }
    }
    public void openKhoanChiFragment(){
        if(mCurrentFragment!=FRAGMENT_KHOANCHI){
            replaceFragment(new KhoanChiFragment());
            mCurrentFragment = FRAGMENT_KHOANCHI;
        }
    }
    public void openDongLucFragment(){
        if(mCurrentFragment!=FRAGMENT_DONGLUC){
            replaceFragment(new DongLucFragment());
            mCurrentFragment = FRAGMENT_DONGLUC;
        }
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();
    }

}