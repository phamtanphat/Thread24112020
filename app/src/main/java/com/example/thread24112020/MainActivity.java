package com.example.thread24112020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Xử lý đồng bộ

        // Tạo thread
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                xuLyIn("A");
            }
        });

        threadA.start();
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                xuLyIn("B");
            }
        });


        threadB.start();

        // Xử lý main thread với background thread : Main thread sẽ chạy trước , background thread
        // Xử lý background thread với background thread : Chưa biết ai sẽ chạy trước

        // Xử lý đồng bộ
        // Cách 1 : Các thread cùng xử lý trên function


    }
    private synchronized void xuLyIn(String name){
        for (int i = 0; i < 1000 ; i++) {
            Log.d("BBB","Thread " + name + " : " + i);
        }
    }
}