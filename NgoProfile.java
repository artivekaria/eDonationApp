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

public class NgoProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    SessionManager sessionManager;
    Context context;
    TextView email,Type2,Type;

    ImageButton nlogout,Setting;
    Button nrequinment,Donor,Material;

    Toolbar mtoolbar;
    DrawerLayout mdrawerlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_profile);
        context = NgoProfile.this;
        sessionManager = new SessionManager(this);


        email = (TextView) findViewById(R.id.uemail);
        Type = (TextView) findViewById(R.id.type);
        Type2 = (TextView) findViewById(R.id.type2);
      //  nlogout= (ImageButton) findViewById(R.id.logout);
      //  Setting= (ImageButton) findViewById(R.id.setting);
       // nrequinment= (Button) findViewById(R.id.requirnment);
        //Material= (Button) findViewById(R.id.material);
       // Donor= (Button) findViewById(R.id.donor);




        Toast.makeText(getApplicationContext(), "User Login Status: " + sessionManager.isLoggedIn(), Toast.LENGTH_LONG).show();


        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetails();
        String nemail = user.get(sessionManager.KEY_EMAIL);

        HashMap<String, String> user1 = sessionManager.getUserDetails();
        String type = user1.get(sessionManager.KEY_TYPE);
        String type2 = user1.get(sessionManager.KEY_TYPE);



       email.setText(nemail);
       Type.setText(type);
        Type2.setText(type2);



        //log= (Button) findViewById(R.id.logout);
        //   HashMap<String, String> user = pref.getUserDetails();
        // final String username = user.get(pref.KEY_USER);


        //uname.setText(name);
        //    uname.setText(username);

        setUpToolbarmenu();
        setUpNavigationMenu();


    }

    public void setUpToolbarmenu()
    {

        mtoolbar= (Toolbar) findViewById(R.id.toolbar);
        mtoolbar.setTitle("Ngo Profile");
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

            case R.id.Reg_Complain:
                Intent intent=new Intent(NgoProfile.this,Requirnment.class);
                startActivity(intent);
                return true;
            case R.id.view_donor:
                Intent intent1=new Intent(NgoProfile.this,donor_list.class);
                startActivity(intent1);
                return true;

            case R.id.add_ngo:
                Intent intent2=new Intent(NgoProfile.this,AddNgo.class);
                startActivity(intent2);
                return true;

            case R.id.add_event:
                Intent intent5=new Intent(NgoProfile.this,add_event.class);
                startActivity(intent5);
                return true;
            case R.id.certificate:
                Intent intent3=new Intent(NgoProfile.this,show_certificte.class);
                startActivity(intent3);
                return true;

            case R.id.view:
                Intent intent4=new Intent(NgoProfile.this,ViewDonorRequirement.class);
                startActivity(intent4);
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
                        Intent intent = new Intent(NgoProfile.this, login.class);
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
        android.support.v7.app.AlertDialog.Builder back = new android.support.v7.app.AlertDialog.Builder(NgoProfile.this);
        back.setTitle("Are you sure want to leave now?");
        back.setCancelable(true)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int arg1) {
                                Intent intent = new Intent(NgoProfile.this, NgoProfile.class);


                                startActivity(intent);
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
