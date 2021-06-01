package com.example.nik.fcnc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class view_certificate extends AppCompatActivity {

    private Button btn;
    private EditText editId;

    private List<GenCertificateHero> listSuperHeroes;

    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private GenCertificateAdapter adapter;

    //public static final String GET_URL = "http://patelnishtha009.000webhostapp.com/FCNC/show_certificate.php";
    //public static final String GET_URL = "http://192.168.43.168/lunchbox/fetch_item_android.php?Email=";
    public static final String REGISTER_URL = "http://patelnishtha009.000webhostapp.com/FCNC/view_generated_certificate.php?donor_email=";
    //public static final String REGISTER_URL = "http://172.16.0.155/FCNC/view_generated_certificate.php?donor_email=";


    public static final String KEY_NGO = "ngo_name";
    public static final String KEY_EMAIL="ngo_email";
    public static final String KEY_NAME = "donor_name";


    public static final String KEY_MONEY= "donet_money";
    public static final String KEY_TEXT = "text";

    // public static final String KEY_NUMBER = "number";
    public static final String KEY_ADDRESS = "address";



    //AppLocationService appLocationService;
    SessionManager sessionManager;
    Context context;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_certificate);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        context = view_certificate.this;
        sessionManager = new SessionManager(this);

        //Initializing our superheroes list
        listSuperHeroes = new ArrayList<>();

        //  appLocationService = new AppLocationService(ShowNgo.this);

        getData();

        //  senaddr();


        //Calling method to get data

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }







    //This method will get data from the web api
    private void getData() {

        HashMap<String, String> user=  sessionManager.getUserDetails();
        String donor_email = user.get(sessionManager.KEY_EMAIL);
        String url = REGISTER_URL + donor_email;
        //Creating a json array request
        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("User", response.toString());



                try {




                    JSONObject responseObj = new JSONObject(response);

                    JSONArray Data = responseObj.getJSONArray("data");
                    parseData(Data);


                } catch (JSONException e) {
                   // Toast.makeText(getApplicationContext(),
                       //     "Error: " + e.getMessage(),
                        //    Toast.LENGTH_LONG).show();

                    /*new AlertDialog.Builder(view_certificate.this)
                            .setTitle("Error!")
                            .setMessage("No Certificate generated. Please go back to your profile.")
                            .setNegativeButton(android.R.string.no, null)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1) {
                                    //MainActivity.super.onBackPressed();
                                    Intent i=new Intent(view_certificate.this,DonorProfile.class);
                                    //i.putExtra("email","d@gmail.com");
                                    startActivity(i);
                                }
                            }).create().show();*/
                    Toast.makeText(getApplicationContext(),"No Certificate generated. Please go back to your profile.",Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("USER", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();


                //progressBar.setVisibility(View.GONE);
                //dialog.dismiss();
            }
        });

        int socketTimeout = 60000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        strReq.setRetryPolicy(policy);
        //Creating request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(strReq);
        // Adding request to request queue
        // MyApplication.getInstance().addToRequestQueue(strReq);

    }

    //This method will parse json data
    private void parseData(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            GenCertificateHero superHero = new GenCertificateHero();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                // superHero.setImage(json.getString(KEY_IMAGE));
                superHero.setNgo_name(json.getString(KEY_NGO));
                superHero.setDonor_name(json.getString(KEY_NAME));
                superHero.setDonet_money(json.getString(KEY_MONEY));
                superHero.setText(json.getString(KEY_TEXT));
                superHero.setAddress(json.getString(KEY_ADDRESS));
                superHero.setNgo_email(json.getString(KEY_EMAIL));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            listSuperHeroes.add(superHero);
        }

        //Finally initializing our adapter
        adapter = new GenCertificateAdapter(listSuperHeroes, view_certificate.this);
        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);


    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder back = new AlertDialog.Builder(view_certificate.this);
        back.setTitle("Are you sure want to leave now?");
        back.setCancelable(true)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int arg1) {
                                Intent intent = new Intent(view_certificate.this, DonorProfile.class);


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

        AlertDialog dialogback = back.create();
        dialogback.show();
    }
    public void endActivity() {
        this.finish();
    }

}


