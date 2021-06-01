package com.example.nik.fcnc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewDonorMoney extends AppCompatActivity {


    SessionManager sessionManager;
    Context context;

    ListView listView;
    DonorMoneyAdapter myAdapter;

    ArrayList<DonorMoneyHero> arrayList = new ArrayList<>();

    public static final String REGISTER_URL = "http://patelnishtha009.000webhostapp.com/FCNC/view_donet_money.php?email=";
    //public static final String REGISTER_URL = "http://172.16.0.155/FCNC/view_donet_money.php?email=";


    public static final String KEY_NGO = "ngo_name";
    //public static final String KEY_EMAIL= "email";
    public static final String KEY_NAME = "donor_name";
    public static final String KEY_D_EMAIL= "donor_email";
    public static final String KEY_PAYMENT= "payment_mode";
    public static final String KEY_MONEY= "donet_money";
    public static final String KEY_NUMBER = "number";
    public static final String KEY_ADDRESS = "address";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_donor_money);
        context = ViewDonorMoney.this;
        sessionManager = new SessionManager(this);

        myAdapter = new DonorMoneyAdapter(getApplicationContext(), R.layout.view_money);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new ListClickHandler());

        getdata();
    }

    public class ListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
            // TODO Auto-generated method stub
            TextView listText = (TextView) view.findViewById(R.id.ngo_name);
            String ngo_name = listText.getText().toString();

            Toast.makeText(getApplicationContext(), ngo_name, Toast.LENGTH_LONG).show();
        }

    }

    public void getdata() {

        // String url = DATA_URL + editTextId.getText().toString().trim();
        HashMap<String, String> user=  sessionManager.getUserDetails();
        String email = user.get(sessionManager.KEY_EMAIL);

        Toast.makeText(ViewDonorMoney.this,email,Toast.LENGTH_LONG).show();
        String url = REGISTER_URL + email;
        //Creating a json array request
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(getApplicationContext(), ""+response, Toast.LENGTH_SHORT).show();

                        int count = 0;
                        while (count < response.length()) {
                            try {
                              //  Toast.makeText(getApplicationContext(), "response", Toast.LENGTH_SHORT).show();
                                JSONObject jsonObject = response.getJSONObject(count);
                                DonorMoneyHero area = new DonorMoneyHero(jsonObject.getString("ngo_name")
                                        ,jsonObject.getString("donor_name"),jsonObject.getString("donor_email"),jsonObject.getString("payment_mode"),jsonObject.getString("donet_money")
                                        ,jsonObject.getString("number"),jsonObject.getString("address"));
                                myAdapter.add(area);
                                arrayList.add(area);


                                count++;
                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(), "Error in Response", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                            // pd.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        // RequestQueue requestQueue = Volley.newRequestQueue(Search_Area.this);
        // requestQueue.add(jsonArrayRequest);

        Config.getmInstance(getApplicationContext()).addReq(jsonArrayRequest);

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder back = new AlertDialog.Builder(ViewDonorMoney.this);
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

