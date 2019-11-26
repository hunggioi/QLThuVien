package com.example.quanlythuvien;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
    public class MainActivity extends AppCompatActivity {
        private TextView dangnhap;
        private TextView thoat;
        private LinearLayout dangky;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main_activity);

            dangnhap = findViewById(R.id.dangnhap);
            thoat = findViewById(R.id.thoat);
            dangky = findViewById(R.id.dangky);

            dangnhap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(com.example.quanlythuvien.MainActivity.this,Dangnhap.class);
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit,
                            R.anim.left_to_right, R.anim.right_to_left);
                    startActivity(intent);
                }
            });

            thoat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            dangky.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(com.example.quanlythuvien.MainActivity.this,Dangki.class);
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit,
                            R.anim.right_to_left, R.anim.left_to_right);
                    startActivity(intent);
                }
            });
        }
    }

