package com.example.nik.fcnc;

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
import java.util.List;

public class ShowEvent extends AppCompatActivity {

    private Button btn;
    private EditText editId;

    private List<EventHero> listSuperHeroes;

    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private EventAdapter adapter;

    //public static final String GET_URL = "http://172.16.0.155/FCNC/show_ngo.php";
    public static final String GET_URL = "http://patelnishtha009.000webhostapp.com/FCNC/show_event.php";
    //public static final String GET_URL = "http://192.168.43.168/lunchbox/fetch_item_android.php?Email=";
    private String KEY_IMAGE= "image";
    private String KEY_EMAIL = "email";
    private String KEY_NAME = "ngo_name";
    private String KEY_EVENT = "event_name";

    private String KEY_NO = "number";

    private String KEY_ADD = "address";

    private String KEY_DESC = "description";
    public static final String KEY_ADDRESS = "locationAddress";

    //AppLocationService appLocationService;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event);
        //Initializing Views
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



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

        //Creating a json array request
        StringRequest strReq = new StringRequest(Request.Method.GET,
                GET_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("User", response.toString());

                try {


                    JSONObject responseObj = new JSONObject(response);

                    JSONArray Data = responseObj.getJSONArray("data");
                    parseData(Data);


                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();


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
            EventHero superHero = new EventHero();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                superHero.setImage(json.getString(KEY_IMAGE));
                superHero.setEmail(json.getString(KEY_EMAIL));
                superHero.setNgo_name(json.getString(KEY_NAME));
                superHero.setEvent_name(json.getString(KEY_EVENT));
                superHero.setNumber(json.getString(KEY_NO));
                superHero.setAddress(json.getString(KEY_ADD));
                superHero.setDescription(json.getString(KEY_DESC));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            listSuperHeroes.add(superHero); //data to model class
        }

        //Finally initializing our adapter
        adapter = new EventAdapter(listSuperHeroes, ShowEvent.this);
        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);


    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder back = new AlertDialog.Builder(ShowEvent.this);
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

        AlertDialog dialogback = back.create();
        dialogback.show();
    }
    public void endActivity() {
        this.finish();
    }
}

