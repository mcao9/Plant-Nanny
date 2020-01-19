package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.myapplication.ui.main.GlobalClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.myapplication.ui.main.SectionsPagerAdapter;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private LineGraphSeries<DataPoint> series1;

    //LineGraphSeries<DataPoint> series;
    AsyncTaskRunner asyncTask =new AsyncTaskRunner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AsyncTaskRunner runner = new AsyncTaskRunner();

        //System.out.println("yasdaeah");

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


        GlobalClass globalClass = (GlobalClass) getApplicationContext();

        List<Integer> results = new ArrayList<Integer>();
        System.out.println("yeah we here");



        //AsyncTaskRunner runner = new AsyncTaskRunner();
        //runner.doInBackground();
        //String koala = runner.getString();

        View v = inflater.inflate(R.layout.fragment_tab1, null);
        Button btn = (Button)v.findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (final View v) {


            }
        });
        String koala = "";
        new AsyncTaskRunner().execute();
        koala = runner.getString();

        System.out.println("send help " + koala);


    }


    public class AsyncTaskRunner extends AsyncTask<Void, Void, String> {
        //String connection = "jdbc:postgresql://35.224.80.243/postgres, postgres, nanny";
        //List<Integer> result = new ArrayList<Integer>();
        public String sql = "";
        //public AsyncResponse delegate = null;

        //@Override
        public String getString()
        {
            execute().get();
            System.out.println("i am here" + sql + "i am here");
            return sql;
        }

        @Override
        protected String doInBackground(Void... params) {
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://35.224.80.243/postgres",
                        "postgres",
                        "nanny");
                if (connection != null) {
                    System.out.println("Connected to the cloud database!");
                }
                String query = "SELECT lightLevel, moistureLevel, carbonDLevel, tVOCLevel, rainlevel FROM sensor WHERE loggedat = (SELECT max(loggedat) FROM sensor)";
                statement = connection.prepareStatement(query);
                statement.execute();
                resultSet = statement.getResultSet();

                while (resultSet.next()) {
                    /*
                    result.add(resultSet.getInt("lightLevel"));
                    result.add(resultSet.getInt("moistureLevel"));
                    result.add(resultSet.getInt("carbonDLevel"));
                    result.add(resultSet.getInt("tVOCLevel"));
                    result.add(resultSet.getInt("rainLevel"));
                     */
                    sql = sql + " " + resultSet.getInt("lightLevel") + resultSet.getInt("moistureLevel") + resultSet.getInt("carbonDLevel") + resultSet.getInt("tVOCLevel") + resultSet.getInt("rainLevel");

                }
                System.out.println(sql + " send help");
                return sql;
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("Error while connection to the cloud database: " + e);
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        System.out.println("Failed to close connection to cloud database: " + e);
                        e.printStackTrace();
                    }
                }
            }
            //return result;
            return sql;
        }
        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            //delegate.processFinish(result);
        }

    }

}

/*
public class psql extends AsyncTask<Void, Void, Void>  {

    @Override
    public Void doInBackground(Void... params)
    {
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://35.224.80.243/postgres",
                    "postgres",
                    "nanny");
            if (connection != null){
                System.out.println("Connected to the cloud database!");
            }
            //updateDatabase ud = new updateDatabase(connection);
            queryDatabase qd = new queryDatabase(connection);
            //PlantStatSet ss = new PlantStatSet();
            //ss.readPort();

            //System.out.println(qd.getSensorData());
            //return qd.getSensorData();
            //System.out.println(ud.insertSensorInfo(ss.getLight(), ss.getSoilMoist(), ss.getCO2(), ss.gettVoc(), ss.getRain()));
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Error while connection to the cloud database: " + e);
            e.printStackTrace();
            //return blank;
        } finally {
            if (connection != null){
                try{
                    connection.close();
                } catch (SQLException e){
                    System.out.println("Failed to close connection to cloud database: " + e);
                    e.printStackTrace();
                    //return blank;
                }
            }
        }
    }
}
 */

