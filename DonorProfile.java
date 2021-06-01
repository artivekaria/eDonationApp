package com.example.nik.fcnc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class DonorProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    SessionManager sessionManager;
    Context context;
    TextView email,Type2,Type;
    Button Ngo,Requirement;
    ImageButton nlogout;
    Toolbar mtoolbar;
    DrawerLayout mdrawerlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_profile);
        context = DonorProfile.this;
        sessionManager = new SessionManager(this);




        email = (TextView) findViewById(R.id.uemail);
        Type = (TextView) findViewById(R.id.type);
       Type2 = (TextView) findViewById(R.id.type2);
      //  Ngo = (Button) findViewById(R.id.ngo);
      //  Requirement = (Button) findViewById(R.id.requirnment);
       // nlogout= (ImageButton) findViewById(R.id.logout);
        Toast.makeText(getApplicationContext(), "User Login Status: " + sessionManager.isLoggedIn(), Toast.LENGTH_LONG).show();


        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
              sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetails();
        String nemail = user.get(sessionManager.KEY_EMAIL);


       email.setText(nemail);
        HashMap<String, String> user1 = sessionManager.getUserDetails();
        String type = user1.get(sessionManager.KEY_TYPE);
        String type2 = user1.get(sessionManager.KEY_TYPE);
        Type.setText(type);
        Type2.setText(type2);
        setUpToolbarmenu();
        setUpNavigationMenu();


    }

    public void setUpToolbarmenu()
    {

        mtoolbar= (Toolbar) findViewById(R.id.toolbar);
        mtoolbar.setTitle("Donor Profile");
    }

    public void  setUpNavigationMenu()
    {
        mdrawerlayout= (DrawerLayout) findViewById(R.id.drawerlayout);
        NavigationView navigationView= (NavigationView) findViewById(R.id.Navigation);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,mdrawerlayout,mtoolbar,
                R.string.drawer_open,R.string.drawer_close);
        mdrawerlayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        closedrawer();

        switch (item.getItemId())
        {

            case R.id.Requirement:
                Intent intent=new Intent(DonorProfile.this,ShowRequirement.class);
                startActivity(intent);
                return true;
            case R.id.Ngo:
                Intent intent1=new Intent(DonorProfile.this,ShowNgo.class);
                startActivity(intent1);
                return true;

            case R.id.event:
                Intent intent4=new Intent(DonorProfile.this,ShowEvent.class);
                startActivity(intent4);
                return true;
            case R.id.certificate:
                Intent intent3=new Intent(DonorProfile.this,view_certificate.class);
                startActivity(intent3);
                return true;

            case R.id.logout:
                logout();
                return true;

        }
        return false;
    }
    public void closedrawer()
    {
        mdrawerlayout.closeDrawer(GravityCompat.START);
    }

    private void logout() {
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {


                        //Starting login activity
                        // Intent intent = new Intent(WomenProfile.this, Login.class);
                        //startActivity(intent);
                        sessionManager.clearSession();
                        Intent intent = new Intent(DonorProfile.this, login.class);
                        intent.putExtra("finish", true);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        sessionManager.logoutUser();

                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        android.support.v7.app.AlertDialog.Builder back = new android.support.v7.app.AlertDialog.Builder(DonorProfile.this);
        back.setTitle("Are you sure want to leave now?");
        back.setCancelable(true)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int arg1) {
                                endActivity();
                            }
                        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        dialog.cancel();
                    }
                });

        android.support.v7.app.AlertDialog dialogback = back.create();
        dialogback.show();
    }
    public void endActivity() {
        this.finish();
    }

}
