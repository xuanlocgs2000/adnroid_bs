package com.example.bai1_multithread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity2 extends AppCompatActivity {
    ProgressBar bar;
    //khai báo handler class để xử lý đa tiến trình
    Handler handler;
    //dùng AtomicBoolean để thay thế cho boolean
    AtomicBoolean isrunning = new AtomicBoolean(false);
    //boolean
    Button btnStart;
    TextView lblmsg;
    Button nextSceen;
    MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAsyncTask.execute();
            }
        });
    }

    class MyAsyncTask extends AsyncTask<Void, Integer, Void> {


        @Override
        protected void onPreExecute() {
            bar.setProgress(0);
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            bar.setProgress(values[0].intValue());
            lblmsg.setText(values[0].toString() + "%");
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void unused) {
            // end thread
            super.onPostExecute(unused);
            //   bar.setProgress(100);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for(int i = 0 ;i <= 100 ; i++) {
                SystemClock.sleep(10);
                publishProgress(i);
            }
            return null;
        }
    }



    private void initView() {
        bar = (ProgressBar) findViewById(R.id.progressBar1);
        lblmsg = findViewById(R.id.textView1);
        btnStart = (Button) findViewById(R.id.btnStart);
        nextSceen = findViewById(R.id.switchScreen);
        myAsyncTask = new MyAsyncTask();
    }
}