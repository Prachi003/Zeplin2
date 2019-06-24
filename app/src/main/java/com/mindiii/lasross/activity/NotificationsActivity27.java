package com.mindiii.lasross.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.mindiii.lasross.R;
import com.mindiii.lasross.adapter.NotificationAdapter;
import com.mindiii.lasross.model.NotificationModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity27 extends AppCompatActivity {

    RecyclerView recyclerView;
    NotificationAdapter notificationAdapter;
    List<NotificationModel> list;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_activity_layout_27);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.home_header_bg1));

        list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            NotificationModel model = new NotificationModel("Beware of fake Orders",
                    "Beware of fake orders and fraudalent tempor incididunt ut " +
                            "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco " +
                            "laboris nisi ut aliquip ex ea commodo consequat","11:07 AM");
            list.add(model);

        }
        notificationAdapter = new NotificationAdapter(list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(notificationAdapter);
    }
}
