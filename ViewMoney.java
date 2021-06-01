package com.example.nik.fcnc;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class ViewMoney extends AppCompatActivity {


    ListView listView;
    MoneyAdapter myAdapter;
    ArrayList<MoneyHero> arrayList = new ArrayList<>();

    public static final String LOGIN_URL = "http://patelnishtha009.000webhostapp.com/FCNC/view_money.php";
    //public static final String LOGIN_URL = "http://172.16.0.155/FCNC/view_money.php";
    public static final String KEY_NGO_NAME = "ngo_name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_MONEY = "money";
    public static final String KEY_DES = "des";
    public static final String KEY_DATE = "jdate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_money);
        myAdapter = new MoneyAdapter(getApplicationContext(), R.layout.view_money);
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

            TextView listText1 = (TextView) view.findViewById(R.id.email);
            String email = listText1.getText().toString();
           Intent intent=new Intent(ViewMoney.this,donate_money.class);
            intent.putExtra("ngo_name",ngo_name);
            intent.putExtra("email",email);
           startActivity(intent);
            Toast.makeText(getApplicationContext(), ngo_name, Toast.LENGTH_LONG).show();

        }

    }

    public void getdata() {

        // String url = DATA_URL + editTextId.getText().toString().trim();

        //Creating a json array request
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(LOGIN_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                        int count = 0;
                        while (count < response.length()) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(count);
                                MoneyHero area = new MoneyHero(jsonObject.getString("ngo_name")
                                        ,jsonObject.getString("money"),jsonObject.getString("des"),jsonObject.getString("jdate")
                                        ,jsonObject.getString("email"));
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
        AlertDialog.Builder back = new AlertDialog.Builder(ViewMoney.this);
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

