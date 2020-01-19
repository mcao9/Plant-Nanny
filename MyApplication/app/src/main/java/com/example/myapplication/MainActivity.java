package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

import android.provider.ContactsContract;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import java.util.*;

import android.app.Activity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.net.Uri;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.example.myapplication.ui.main.SectionsPagerAdapter;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private LineGraphSeries<DataPoint> series1;

    //LineGraphSeries<DataPoint> series;
    Button weather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        /*
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

         */
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.constraintLayout);
        //setContentView(R.layout.fragment_tab2);
        LayoutInflater inflater = this.getLayoutInflater();
        View tab2 = inflater.inflate(R.layout.fragment_tab2, mainLayout, false);

        weather = tab2.findViewById(R.id.button);
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.weather.com"));
                startActivity(intent);
            }
        });
        //graph stuff
        //setContentView(R.layout.fragment_tab1);



    }

}