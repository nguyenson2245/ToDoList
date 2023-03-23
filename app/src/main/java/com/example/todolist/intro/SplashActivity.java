package com.example.todolist.intro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.todolist.MainActivity;
import com.example.todolist.R;

public class SplashActivity extends AppCompatActivity {

    ImageView splashing;
    TextView appname;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        appname =findViewById(R.id.appname);
        lottieAnimationView = findViewById(R.id.lottie);
        appname.animate().translationY(2000).setDuration(1000).setStartDelay(3000);
        lottieAnimationView.animate().setDuration(1000).setStartDelay(3000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        },4000);
    }
}
