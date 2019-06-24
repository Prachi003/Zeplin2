package com.mindiii.lasross.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;

import com.mindiii.lasross.R;
import com.mindiii.lasross.adapter.FAQAdapter;
import com.mindiii.lasross.model.FAQModel;

import java.util.ArrayList;
import java.util.List;

public class FAQActivity28 extends AppCompatActivity {

    RecyclerView recyclerView;
    List<FAQModel> list;
    FAQAdapter faqAdapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq_activty_28);

        recyclerView = findViewById(R.id.recylerView);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.home_header_bg1));

        list = new ArrayList<>();

        for (int i = 1; i <= 2; i++) {
            FAQModel itemList = new FAQModel("How long does it take to receive my order?",
                    "Meditation williamsburg kogi blog bushwick pitchfork polaroid austin dreamcatcher narwhal taxidermy tofu gentrify aesthetic.");
            list.add(itemList);
        }
        faqAdapter = new FAQAdapter(list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(faqAdapter);
    }
}

