package com.example.todolist.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.amrdeveloper.lottiedialog.LottieDialog;
import com.example.todolist.CatActivity;
import com.example.todolist.MainActivity;
import com.example.todolist.MainActivityToDoList;
import com.example.todolist.R;
import com.example.todolist.adapter.PhotoAdapter;
import com.example.todolist.model.Photo;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment {

    String strNameFeedBack, strEmailFeedback,strFeedback;
    private List<Photo> mlistPhoto;
    private LottieAnimationView catAnimation,discordAnimation,feedbackAnimation,todolisst;
    private CircleIndicator circleIndicator;
    private ViewPager viewPager;
    private LottieDialog lottieDialog;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager.getCurrentItem()==mlistPhoto.size()-1){
                viewPager.setCurrentItem(0);
            }
            else {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.viewPagerHome);
        circleIndicator = view.findViewById(R.id.circle_indicator);
        catAnimation = view.findViewById(R.id.lottieAnimationView2);
        discordAnimation = view.findViewById(R.id.lottieAnimationView);
        feedbackAnimation = view.findViewById(R.id.lottieAnimationView4);
        todolisst = view.findViewById(R.id.lottieAnimationViewTODOLISt);

        lottieDialog = new LottieDialog(getContext());

        lottieDialog.setAnimation(R.raw.feedback)
                .setAutoPlayAnimation(true)
                .setMessage("Please wait to sending your feedback...")
                .setDialogBackground(Color.WHITE)
                .setMessageColor(Color.BLACK)
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .setAnimationRepeatCount(LottieDialog.INFINITE)
                .setDialogWidth(600)
                .setDialogHeight(300);

        mlistPhoto = getListPhoto();
        PagerAdapter adapter = new PhotoAdapter(mlistPhoto);
        viewPager.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager);
        handler.postDelayed(runnable,4000);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,4000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        catAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CatActivity.class));
            }
        });

        discordAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://discord.gg/4e2m26C4");
            }
        });

        todolisst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainActivityToDoList.class));
            }
        });

    }
    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.d1));
        list.add(new Photo(R.drawable.d2));
        list.add(new Photo(R.drawable.d4));
        list.add(new Photo(R.drawable.d5));
        list.add(new Photo(R.drawable.bookbanner1));
        list.add(new Photo(R.drawable.img_10));
        return list;

    }
}