package com.example.nik.fcnc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Home extends AppCompatActivity {

    ImageButton donor,ngo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        donor= (ImageButton) findViewById(R.id.Donorid);
        ngo= (ImageButton) findViewById(R.id.Ngoid);

        donor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Home.this,login.class);
                startActivity(intent);

            }
        });


        ngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Home.this,login.class);
                startActivity(intent);

            }
        });
    }
}
