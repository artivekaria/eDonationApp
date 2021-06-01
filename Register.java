package com.example.nik.fcnc;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity implements View.OnClickListener {

    public static final String REGISTER_URL = "http://patelnishtha009.000webhostapp.com/FCNC/register.php";
    //public static final String REGISTER_URL = "http://172.16.0.155/FCNC/register.php";

    public static final String KEY_FNAME = "fname";
    public static final String KEY_LNAME = "lname";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_NUMBER = "number";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_TYPE = "type";

    private EditText ufname, ulname, uemail, uaddress, upassword, unumber;
    private Spinner usertype;

    private Button userreg;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        String[] group = {"NGO", "DONOR"};


        ufname = (EditText) findViewById(R.id.fname);
        ulname = (EditText) findViewById(R.id.lname);
        uemail = (EditText) findViewById(R.id.email);
        upassword = (EditText) findViewById(R.id.password);
        unumber = (EditText) findViewById(R.id.number);
        uaddress = (EditText) findViewById(R.id.address);
        usertype = (Spinner) findViewById(R.id.Usertype);
        userreg = (Button) findViewById(R.id.Toregister);


        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, group);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        usertype.setAdapter(aa);


        usertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        userreg.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {


        final String fname = ufname.getText().toString().trim();
        final String lname = ulname.getText().toString().trim();


        final String email = uemail.getText().toString().trim();

        final String password = upassword.getText().toString().trim();
        final String number = unumber.getText().toString().trim();
        final String address = uaddress.getText().toString().trim();
        final String type = usertype.getSelectedItem().toString();


        ufname.getText().clear();
        ulname.getText().clear();
        uemail.getText().clear();
        upassword.getText().clear();
        unumber.getText().clear();
        uaddress.getText().clear();


        if (fname.length() == 0) {
            Toast.makeText(Register.this, "Please Enter Firstname", Toast.LENGTH_LONG).show();
        } else {
            if (lname.length() == 0) {
                Toast.makeText(Register.this, "Please Enter lastname", Toast.LENGTH_LONG).show();

            } else {
                if (!isValidEmail(email)) {
                    Toast.makeText(Register.this, "Please Enter Valid Email ID", Toast.LENGTH_LONG).show();

                } else {
                    if (!isValidPassword(password)) {

                        Toast.makeText(Register.this, "Please Enter Password", Toast.LENGTH_LONG).show();
                    } else {
                        if (!isValidNumber(number)) {
                            Toast.makeText(Register.this, "Password Are Not Matched", Toast.LENGTH_LONG).show();
                        } else {
                            if (address.length() == 0) {
                                Toast.makeText(Register.this, "Please Enter Assress", Toast.LENGTH_LONG).show();
                            } else {

                                if (type.length() == 0) {
                                    Toast.makeText(Register.this, "Please Enter type", Toast.LENGTH_LONG).show();
                                } else {

                                    registerUser(fname, lname, email, password, number, address, type);

                                }

                            }
                        }
                    }

                }
            }
        }
    }


    public void registerUser(final String fname,final String lname, final String email,
                             final String password,final String number,final  String address,final String type) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(Register.this, response, Toast.LENGTH_LONG).show();
                        //Log.v("response 1","#"+response);

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String message = jsonObject.getString("message");      //key
                            String code = jsonObject.getString("code");
                            if (code.equalsIgnoreCase("success")) {
                                //women_id = jsonObject.getString("women_id");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            } else if (code.equalsIgnoreCase("failed")) {
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            }
                            Intent ver = new Intent(Register.this, login.class);
                            //ver.putExtra("women_id", women_id);
                            //ver.putExtra("mobile", mobile);
                           // ver.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            // dialog.dismiss();
                            startActivity(ver);
                            endActivity();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Response Error !!", Toast.LENGTH_LONG).show();
                            //dialog.dismiss();
                        }





                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("Error 1","#"+error.toString());
                        Toast.makeText(Register.this, error.toString(), Toast.LENGTH_LONG).show();
                    }

                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                params.put(KEY_FNAME,fname);
                params.put(KEY_LNAME,lname);
                params.put(KEY_EMAIL,email);
                params.put(KEY_PASSWORD,password);
                params.put(KEY_NUMBER,number);
                params.put(KEY_ADDRESS,address);
                params.put(KEY_TYPE,type);


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



    private boolean isValidEmail (String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;

    }

    private boolean isvalidusername(String name) {
        String USER_PATTERRN="\"^[A-Za-z\\\\s]{1,}[\\\\.]{0,1}[A-Za-z\\\\s]{0,}$\"";
        Pattern pattern = Pattern.compile(USER_PATTERRN);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();


    }

    private boolean isValidNumber(String number) {
        if (number != null && number.length()==10) {
            return true;
        }
        return false;
    }


    public void endActivity() {
        this.finish();
    }

}
