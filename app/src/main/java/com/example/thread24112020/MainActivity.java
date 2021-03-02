package com.example.thread24112020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    int a, b, c;
    MyFlag myFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = b = c = 0;

        myFlag = new MyFlag(1);
        // Xử lý main thread với background thread : Main thread sẽ chạy trước , background thread
        // Xử lý background thread với background thread : Chưa biết ai sẽ chạy trước

        // Xử lý đồng bộ
        // Cách 1 : Các thread cùng xử lý trên function
        // Cách 2 : Quản lý trình tự chạy thread

        // Thread a : in 1 số (1)
        // Thread b : in 1 số (2)
        // Thread c : tổng của a và b (3)

        // Đặt biến cờ
        // Cờ : 1 thì thằng A chạy
        // Cờ : 2 thì thằng B chạy
        // Cờ : 3 thì thăng C chạy

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myFlag) {
                    for (int i = 0; i < 50; ) {
                        if (myFlag.count == 1){
                            a = i;
                            Log.d("BBB", "Giá trị của a = " + a);
                            myFlag.count = 2;
                            myFlag.notifyAll();
                            i++;
                        }else {
                            try {
                                myFlag.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }


                }

            }
        });


        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myFlag) {
                    for (int i = 0; i < 50;) {
                        if (myFlag.count == 2){
                            b = i;
                            Log.d("BBB", "Giá trị của b = " + b);
                            myFlag.count = 3;
                            myFlag.notifyAll();
                            i++;
                        }else {
                            try {
                                myFlag.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            }
        });


        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myFlag) {
                    for (int i = 0; i < 50;) {
                        if (myFlag.count == 3){
                            c = a + b;
                            Log.d("BBB", "C = " + c);
                            myFlag.count = 1;
                            myFlag.notifyAll();
                            i++;
                        }else {
                            try {
                                myFlag.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();


    }
}