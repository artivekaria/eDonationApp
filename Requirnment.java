package com.example.nik.fcnc;

import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.Menu;
import android.widget.TabHost;

import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class Requirnment extends TabActivity {

    // TabSpec Names
    TabHost tabHost;
    SessionManager sessionManager;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirnment);

        context = Requirnment.this;
        sessionManager = new SessionManager(this);

        TabHost tabHost = getTabHost();


        TabHost.TabSpec tabSpec = tabHost.newTabSpec("Money"); // Create a new TabSpec using tab host
        tabSpec.setIndicator("Money"); // set the “Tab 1” as an indicator
        Intent intent = new Intent(this, Money.class);
        tabSpec.setContent(intent);
        tabHost.addTab(tabSpec); // add first tab in Tab widget

        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("Material"); // Create a new TabSpec using tab host
        tabSpec1.setIndicator("Material"); // set the “Tab 2” as an indicator
        Intent intent1 = new Intent(this, Material.class);
        tabSpec1.setContent(intent1); // specify an intent to use to launch an activity as the tab content

        tabHost.addTab(tabSpec1); // add second tab in Tab widget
        //tabHost.addTab(spec);


        //set tab which one you want to open first time 0 or 1 or 2
        tabHost.setCurrentTab(1);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                // display the name of the tab whenever a tab is changed
                Toast.makeText(getApplicationContext(), tabId, Toast.LENGTH_SHORT).show();
            }
        });


    }


}

