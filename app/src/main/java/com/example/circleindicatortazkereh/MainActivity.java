package com.example.circleindicatortazkereh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator2;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    CircleIndicator2 indicator;

    MyAdapter adapter;
    int count = 0;

    Timer timer;
    final List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);
        indicator = findViewById(R.id.indicator);



        list.add("اولین تکست");
        list.add("دومین تکست");
        list.add("سومین تکست");
        list.add("چهارمین تکست");


        indicator.createIndicators(list.size(),0);
//        indicator.animatePageSelected(0);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter(list, MainActivity.this);
        recyclerView.setAdapter(adapter);


        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recyclerView);
        CircleIndicator2 indicator = findViewById(R.id.indicator);
        indicator.attachToRecyclerView(recyclerView, pagerSnapHelper);
// optional
        adapter.registerAdapterDataObserver(indicator.getAdapterDataObserver());


    }




    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (count >= list.size()) {
                        count = list.size() - 1;
                    }
                    if ((count) == (list.size() - 1)) {

                        indicator.animatePageSelected(count);
                        recyclerView.scrollToPosition(count);
                        count = 0;

                    } else {
                        recyclerView.scrollToPosition(count);
                        indicator.animatePageSelected(count);
                        count = count + 1;
                    }
                }
            });
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 1500, 1500);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null)
            timer.cancel();
    }
}