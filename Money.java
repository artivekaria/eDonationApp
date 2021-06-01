package com.example.nik.fcnc;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Money extends AppCompatActivity implements View.OnClickListener{

    public static final String LOGIN_URL = "http://patelnishtha009.000webhostapp.com/FCNC/moneyreq.php";
    //public static final String LOGIN_URL = "http://172.16.0.155/FCNC/moneyreq.php";

    public static final String KEY_NGO_NAME = "ngo_name";
    public static final String KEY_NAME = "money";
    public static final String KEY_DES = "des";
    public static final String KEY_DATE = "jdate";
    public static final String KEY_EMAIL = "email";


    private EditText editTextm;
    private EditText editTextr;
    private EditText Ngo_name;
    private EditText editTextdate;

    TextView Email;
    SessionManager session;
    Context context;
    private int mYear, mMonth, mDay;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        editTextm = (EditText) findViewById(R.id.mreq);


        editTextr = (EditText) findViewById(R.id.description);



        editTextdate = (EditText) findViewById(R.id.date);
        context = Money.this;
        session = new SessionManager(this);
        Ngo_name = (EditText) findViewById(R.id.ngo_name);


        Email = (TextView) findViewById(R.id.ngo_email);

        HashMap<String, String> user = session.getUserDetails();
        String email = user.get(session.KEY_EMAIL);





        Email.setText(email);
        buttonRegister = (Button) findViewById(R.id.Toregister);
        buttonRegister.setOnClickListener(this);
        editTextdate.setOnClickListener(this);

    }


    private void AddData() {

        final String ngo_name = Ngo_name.getText().toString().trim();
        final String money = editTextm.getText().toString().trim();
        final String des = editTextr.getText().toString().trim();


        final String jdate = editTextdate.getText().toString().trim();
        HashMap<String, String> user = session.getUserDetails();
        final String Email = user.get(session.KEY_EMAIL);

        Ngo_name.getText().clear();
        editTextm.getText().clear();
        editTextr.getText().clear();


        editTextdate.getText().clear();


        if (money.length() == 0) {
            Toast.makeText(Money.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
        } else {
            if (ngo_name.length() == 0) {
                Toast.makeText(Money.this, "Please Enter ngo name", Toast.LENGTH_SHORT).show();


            } else {
                if (des.length() == 0) {
                    Toast.makeText(Money.this, "Please Enter Valid Email ID", Toast.LENGTH_SHORT).show();


                } else {


                    registerUser(ngo_name,money, des, jdate,Email);
                    //registerUser( phone,type);

                }


            }
        }
    }



    public void registerUser(final String ngo_name,final String money, final String des, final String jdate, final String Email) {

        // public void registerUser(final String phone, final String type){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /*Toast.makeText(Register.this, response, Toast.LENGTH_LONG).show();
                        Log.v("response 1","#"+response);
                        openProfile();*/


                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String message = jsonObject.getString("message");
                            String code = jsonObject.getString("code");
                            if (code.equalsIgnoreCase("success")) {

                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                                endActivity();
                            } else if (code.equalsIgnoreCase("failed")) {
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
                        Log.v("Error 1", "#" + error.toString());
                        Toast.makeText(Money.this, error.toString(), Toast.LENGTH_LONG).show();
                    }

                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put(KEY_NGO_NAME, ngo_name);
                params.put(KEY_NAME, money);
                params.put(KEY_DES, des);

                params.put(KEY_DATE, jdate);
                params.put(KEY_EMAIL, Email);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }


    /**
     * private boolean isValidPhone(String phone)
     * {
     * String PHONE_PATTERN = "^[2-9]{2}[0-9]{8}$";
     * <p>
     * Pattern pattern = Pattern.compile(PHONE_PATTERN);
     * Matcher matcher = pattern.matcher(phone);
     * return matcher.matches();
     * <p>
     * }
     **/


    public void date() {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(Money.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox
                        String month = "";
                        if ((monthOfYear + 1) < 10)
                            month = "0" + (monthOfYear + 1);
                        else
                            month = String.valueOf((monthOfYear + 1));

                        String day = "";
                        if (dayOfMonth < 10)
                            day = "0" + dayOfMonth;
                        else
                            day = String.valueOf(dayOfMonth);

                        editTextdate.setText(day + "/"
                                + (month) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
        dpd.show();

    }


    @Override
    public void onClick(View v) {

        if (v == buttonRegister) {
            AddData();
        }


        if (v == editTextdate) {
            date();
        }
    }


    public void endActivity() {
        this.finish();
    }
}


