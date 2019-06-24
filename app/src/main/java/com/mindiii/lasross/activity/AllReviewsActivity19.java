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
import com.mindiii.lasross.adapter.AllReviewsAdapter;
import com.mindiii.lasross.model.AllReviewsModel;

import java.util.ArrayList;
import java.util.List;

public class AllReviewsActivity19 extends AppCompatActivity {

    RecyclerView rvReviewsList;
    AllReviewsAdapter allReviewsAdapter;
    List<AllReviewsModel> list;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_reviews_activity_layout_19);

        rvReviewsList = findViewById(R.id.rvReviewsList);
        rvReviewsList.setHasFixedSize(true);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.home_header_bg1));

        list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            AllReviewsModel itemList = new AllReviewsModel("Pearle Desrosier", "21 Apr 2019","At vero eos et accusamus et iusto odio dignissimos ducimus qui blan diti is praese ntium voluptatum deleniti atque corrupti quos dolores et qu as molestias excepturi sint occaecati cupiditate non provi dent, simili que sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga");
                                list.add(itemList);

        }
        allReviewsAdapter = new AllReviewsAdapter(list, this);
        rvReviewsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvReviewsList.setAdapter(allReviewsAdapter);

    }
}
