package com.mindiii.lasross.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mindiii.lasross.R;

public class ThreadDemoActivity extends AppCompatActivity {

    Button btnStart,btnStop;
    TextView tvText;
    DoSomethingThread randomWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_demo_layout);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        tvText = findViewById(R.id.tvText);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomWork = new DoSomethingThread();
                randomWork.start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomWork.interrupt();
                updateResults("off");
            }
        });
    }

    public void updateResults(String results) {
        tvText.setText(results);
    }

    public class DoSomethingThread extends Thread
    {
        private static final String TAG = "DoSomethingThread";
        private static final int DELAY = 2000;
        private static final int RANDOM_MULTIPLIER = 10;

        @Override
        public void run() {
            Log.e(TAG, "doing work in Random Number Thread");
            while (true) {
                final int randNum = (int) (Math.random() * RANDOM_MULTIPLIER);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateResults(String.valueOf(randNum));
                    }
                });
                //publishProgress(randNum);
                //updateResults(String.valueOf(randNum));
                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    Log.e(TAG,"Interrupting and stopping the Random Number Thread");
                    return;
                }
            }
        }
        private void publishProgress(int randNum) {
            Log.v(TAG, "reporting back from the Random Number Thread");
            final String text = String.valueOf(randNum);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    updateResults(text);
                }
            });
        }

    }



}
