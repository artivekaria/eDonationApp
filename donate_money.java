package com.example.nik.fcnc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class donate_money extends AppCompatActivity implements View.OnClickListener{

    SessionManager sessionManager;
    Context context;
    public static final String REGISTER_URL = "http://patelnishtha009.000webhostapp.com/FCNC/donet_money.php";
    //public static final String REGISTER_URL = "http://172.16.0.155/FCNC/donet_money.php";


    public static final String KEY_NGO = "ngo_name";
    public static final String KEY_EMAIL= "email";
    public static final String KEY_NAME = "donor_name";
    public static final String KEY_D_EMAIL= "donor_email";
    public static final String KEY_PAYMENT= "payment_mode";
    public static final String KEY_MONEY= "donet_money";
    public static final String KEY_NUMBER = "number";
    public static final String KEY_ADDRESS = "address";

    TextView Ngo_name,Email,Donor_email;
    EditText Donor_name,D_number,Address,Money;
    Spinner Mode;

    private Button userreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_money);
        context = donate_money.this;
        sessionManager = new SessionManager(this);

        Mode= (Spinner) findViewById(R.id.payemnt_mode);
       Ngo_name= (TextView) findViewById(R.id.ngo_name);

       Email= (TextView) findViewById(R.id.email);
        Bundle extras = getIntent().getExtras();

        String value = extras.getString("ngo_name");
        String value1 = extras.getString("email");

        Ngo_name.setText(value);
        Email.setText(value1);

        Donor_email=(TextView)findViewById(R.id.doner_email);
        Donor_name=(EditText)findViewById(R.id.donor_name);
        Money=(EditText)findViewById(R.id.donet_money);
        D_number=(EditText)findViewById(R.id.number);
        Address=(EditText)findViewById(R.id.address);
        userreg = (Button) findViewById(R.id.Toregister);
        HashMap<String, String> user = sessionManager.getUserDetails();
        String donor_email = user.get(sessionManager.KEY_EMAIL);


        Donor_email.setText(donor_email);

        String[] group = {"Online", "Offline"};
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, group);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        Mode.setAdapter(aa);


        Mode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


        final String ngo_name = Ngo_name.getText().toString().trim();
        final String email = Email.getText().toString().trim();
        final String donor_name = Donor_name.getText().toString().trim();
        // final String donor_email = Donor_email.getText().toString().trim();
        HashMap<String, String> user = sessionManager.getUserDetails();
        final String donor_email = user.get(sessionManager.KEY_EMAIL);

        final String donet_money = Money.getText().toString().trim();

        final String address = Address.getText().toString().trim();

        final String number = D_number.getText().toString().trim();
        final String payment_mode = Mode.getSelectedItem().toString();


        Donor_name.getText().clear();
        //Donor_email.getText().clear();
        Money.getText().clear();
        Address.getText().clear();
        D_number.getText().clear();


        if (donor_name.length() == 0) {
            Toast.makeText(donate_money.this, "Please Enter Firstname", Toast.LENGTH_LONG).show();
        } else {
            if (!isValidEmail(donor_email)) {
                Toast.makeText(donate_money.this, "Please Enter Valid Email ID", Toast.LENGTH_LONG).show();

            } else {
                if (donet_money.length() == 0) {
                    Toast.makeText(donate_money.this, "Please Enter moeny", Toast.LENGTH_LONG).show();

                } else {
                    if (!isValidNumber(number)) {
                        Toast.makeText(donate_money.this, "Enter number", Toast.LENGTH_LONG).show();
                    } else {
                        if (payment_mode.length() == 0) {
                            Toast.makeText(donate_money.this, "Please Enter payment  mode", Toast.LENGTH_LONG).show();
                        } else {
                            if (address.length() == 0) {
                                Toast.makeText(donate_money.this, "Please Enter Assress", Toast.LENGTH_LONG).show();
                            } else {

                                registerUser(ngo_name, email, donor_name, donor_email, payment_mode, donet_money, number, address);

                            }

                        }
                    }
                }

            }
        }
    }




    public void registerUser(final String ngo_name,final String email, final String donor_name,
                             final String donor_email,final String payment_mode,final String donet_money,final String number,final  String address) {

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
                            Intent ver = new Intent(donate_money.this, payment.class);
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
                        Toast.makeText(donate_money.this, error.toString(), Toast.LENGTH_LONG).show();
                    }

                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                params.put(KEY_NGO,ngo_name);
                params.put(KEY_EMAIL,email);
                params.put(KEY_NAME,donor_name);
                params.put(KEY_D_EMAIL,donor_email);
                params.put(KEY_PAYMENT,payment_mode);
                params.put(KEY_MONEY,donet_money);
                params.put(KEY_NUMBER,number);
                params.put(KEY_ADDRESS,address);



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



    private boolean isValidEmail (String Donor_email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(Donor_email);
        return matcher.matches();
    }


    private boolean isvalidusername(String name) {
        String USER_PATTERRN="\"^[A-Za-z\\\\s]{1,}[\\\\.]{0,1}[A-Za-z\\\\s]{0,}$\"";
        Pattern pattern = Pattern.compile(USER_PATTERRN);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder back = new AlertDialog.Builder(donate_money.this);
        back.setTitle("Are you sure want to leave now?");
        back.setCancelable(true)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int arg1) {
                                Intent intent = new Intent(donate_money.this, DonorProfile.class);


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


