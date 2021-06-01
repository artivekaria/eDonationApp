package com.example.nik.fcnc;

import android.content.Context;
import android.content.Intent;
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

import com.android.volley.AuthFailureError;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login extends AppCompatActivity  {

    public static final String LOGIN_URL = "http://patelnishtha009.000webhostapp.com/FCNC/login.php";
    //public static final String LOGIN_URL = "http://172.16.0.155/FCNC/login.php";

    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_TYPE = "type";


    private EditText u_email, u_password;
     Button ulogin, uregister;
    private Spinner u_type;

    SessionManager sessionManager;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    //to display data from shared preference login to home page and to access shared preference constructor
        context = login.this;
        sessionManager = new SessionManager(this);

        boolean finish = getIntent().getBooleanExtra("finish", false);
        if (finish) {
            startActivity(new Intent(context, login.class));
            finish();
            return;
        }
        u_email = (EditText) findViewById(R.id.email);
        u_password = (EditText) findViewById(R.id.password);
        u_type = (Spinner) findViewById(R.id.Usertype);

        ulogin = (Button) findViewById(R.id.login);
        uregister = (Button) findViewById(R.id.register);


        String[] group = {"NGO", "DONOR"};
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, group);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        u_type.setAdapter(aa);

        u_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        uregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(login.this, Register.class);
                startActivity(intent);


            }
        });

        ulogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                 String email = u_email.getText().toString().trim();
                 String password = u_password.getText().toString().trim();
                 String type = u_type.getSelectedItem().toString();

                u_email.getText().clear();
                u_password.getText().clear();
                // u_type.getText().clear();

                if (!isValidEmail(email)) {
                    Toast.makeText(login.this, "Please Enter Valid Email ", Toast.LENGTH_SHORT).show();
                } else {
                    if (!isValidPassword(password)) {
                        Toast.makeText(login.this, "Please Enter Valid Password", Toast.LENGTH_SHORT).show();

                    } else {
                        registerUser(email,password,type);


                    }


                }
            }

        });
    }

    public  void registerUser(final String email, final String password, final String type)
    {


            StringRequest stringRequest = new StringRequest(Request.Method.POST,LOGIN_URL,
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

                                        // Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                        // sessionManager.createLoginSession(email,password,type);
                                        // Intent ver = new Intent(login.this,NgoProfile.class);


                                        //  startActivity(ver);
                                        //  endActivity();


                                    if (type.equals("NGO")) {

                                        Intent intent = new Intent(login.this, NgoProfile.class);
                                        sessionManager.createLoginSession(email, password, type);
                                        startActivity(intent);
                                        endActivity();
                                    } else if (type.equals("DONOR")) {
                                        Intent intent1 = new Intent(login.this, DonorProfile.class);
                                        sessionManager.createLoginSession(email, password, type);

                                        startActivity(intent1);
                                        endActivity();
                                    } else {
                                        Toast.makeText(login.this, "Enter Data Again", Toast.LENGTH_LONG).show();
                                    }
                                }else if (code.equalsIgnoreCase("failed")) {
                                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                    }

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
                                Toast.makeText(login.this, error.toString(), Toast.LENGTH_LONG).show();
                            }

                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();



                        params.put(KEY_EMAIL,email);
                        params.put(KEY_PASSWORD,password);

                        params.put(KEY_TYPE,type);


                        return params;
                    }

                };



                RequestQueue requestQueue = Volley.newRequestQueue(login.this);
                requestQueue.add(stringRequest);

            }



    private boolean isValidEmail (String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String password) {
        if (password != null && password.length() > 6) {
            return true;
        }
        return false;
    }

    public void endActivity()
    {
        this.finish();
    }




    }


