package com.example.nik.fcnc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Acknowledgement extends AppCompatActivity implements View.OnClickListener{

    public static final String REGISTER_URL = "http://patelnishtha009.000webhostapp.com/FCNC/certificate.php";
    //public static final String REGISTER_URL = "http://172.16.0.155/FCNC/certificate.php";

    public static final String KEY_NGO = "ngo_name";

    public static final String KEY_NAME = "donor_name";


    public static final String KEY_MONEY= "donet_money";
    public static final String KEY_TEXT = "text";

   // public static final String KEY_NUMBER = "number";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_EMAIL = "donor_email";
    public static final String EMAIL = "ngo_email";


    TextView Ngo_name,Email,Donor_money,Donor_name,Address,Donor_email;
    EditText Text;
   Button Btn;

    SessionManager session;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acknowledgement);

        Ngo_name= (TextView) findViewById(R.id.ngo_name);
   //     Donor_email= (TextView) findViewById(R.id.doner_email);
        Donor_name= (TextView) findViewById(R.id.donor_name);
         Text= (EditText) findViewById(R.id.ngopurpose);
        Donor_money= (TextView) findViewById(R.id.donet_money);
        Address= (TextView) findViewById(R.id.address);
       Donor_email= (TextView) findViewById(R.id.Email);

        Btn = (Button) findViewById(R.id.btn);

        Btn.setOnClickListener(this);

        context = Acknowledgement.this;
        session = new SessionManager(this);

        Bundle extras = getIntent().getExtras();

        String value = extras.getString("ngo_name");
     //   String value1 = extras.getString("doner_email");
        String value3 = extras.getString("donor_name");
        String value4 = extras.getString("address");
        String value6 = extras.getString("donor_email");
        String value5 = extras.getString("donet_money");
        Ngo_name.setText(value);
//        Donor_email.setText(value1);

        Donor_name.setText(value3);
        Donor_name.setText(value3);
        Address.setText(value4);
        Donor_email.setText(value6);
        Donor_money.setText(value5);

    }


    private void upload() {


        final String ngo_name = Ngo_name.getText().toString().trim();

        final String donor_name = Donor_name.getText().toString().trim();
        final String donet_money = Donor_money.getText().toString().trim();

        final String text = Text.getText().toString().trim();


        final String address = Address.getText().toString().trim();
        final String donor_email = Donor_email.getText().toString().trim();
        HashMap<String, String> user = session.getUserDetails();
        final String ngo_email = user.get(session.KEY_EMAIL);




        if (ngo_name.length() == 0) {
            Toast.makeText(Acknowledgement.this, "Please Enter Ngo name", Toast.LENGTH_LONG).show();
        } else {
            if (address.length() == 0) {
                Toast.makeText(Acknowledgement.this, "Please Enter Assress", Toast.LENGTH_LONG).show();
            } else {
                if (donet_money.length() == 0) {
                    Toast.makeText(Acknowledgement.this, "Please Enter Assress", Toast.LENGTH_LONG).show();
                } else {

                    if (donor_email.length() == 0) {
                        Toast.makeText(Acknowledgement.this, "Please Enter description", Toast.LENGTH_LONG).show();
                    } else {

                        if (text.length() == 0) {
                            Toast.makeText(Acknowledgement.this, "Please Enter description", Toast.LENGTH_LONG).show();
                        } else {

                            registerUser(ngo_name, donor_name, donet_money, text, address, donor_email, ngo_email);

                        }

                    }
                }
            }
        }

    }



    public void registerUser(final String ngo_name,final String donor_name, final String donet_money,final String text,
                             final String address, final String donor_email,final String ngo_email) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(Register.this, response, Toast.LENGTH_LONG).show();
                        //Log.v("response 1","#"+response);

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String message = jsonObject.getString("message");
                            String code = jsonObject.getString("code");
                            if (code.equalsIgnoreCase("success")) {
                                //women_id = jsonObject.getString("women_id");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            } else if (code.equalsIgnoreCase("failed")) {
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            }
                            Intent ver = new Intent(Acknowledgement.this, NgoProfile.class);
                            //ver.putExtra("women_id", women_id);
                            //ver.putExtra("mobile", mobile);
                            // ver.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            // dialog.dismiss();
                            startActivity(ver);
                            //endActivity();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Add NGO!!", Toast.LENGTH_LONG).show();
                            //dialog.dismiss();
                        }





                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("Error 1","#"+error.toString());
                        Toast.makeText(Acknowledgement.this, error.toString(), Toast.LENGTH_LONG).show();
                    }

                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();



                params.put(KEY_NGO,ngo_name);
                params.put(KEY_NAME,donor_name);
                params.put(KEY_MONEY,donet_money);
                params.put(KEY_TEXT,text);
                params.put(KEY_ADDRESS,address);
                params.put(KEY_EMAIL,donor_email);
                params.put(EMAIL,ngo_email);



                return params;
            }

        };

        /*RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/

        //Config.getmInstance(getApplicationContext()).addReq(req);
        //Config.getmInstance(getApplicationContext()).addReq(stringRequest);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }


    @Override
    public void onClick(View v) {

        if (v == Btn) {
            upload();
        }




    }

}
