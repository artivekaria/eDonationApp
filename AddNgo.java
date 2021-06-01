package com.example.nik.fcnc;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNgo extends AppCompatActivity implements View.OnClickListener{

    private TextView buttonChoose;
    private Button signup;

    private ImageView imageView;
    private TextView Email;
    private EditText Ngo_name,Number,Address,Desc;




    Bundle extras;

    String email;

    SessionManager pref;
    Context context;

   // SessionNgo sessionManager;

    //DatePicker picker;

    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;
    private static final int REQUEST_WRITE_PERMISSION = 786;


    // private String UPLOAD_URL = "http://krishna25.esy.es/host farm/addseed.php";
   private String UPLOAD_URL = "http://patelnishtha009.000webhostapp.com/FCNC/add_ngo.php";
    //private String UPLOAD_URL = "http://192.168.43.220/FCNC/add_ngo.php";

    private String KEY_IMAGE= "image";
    private String KEY_EMAIL = "email";
    private String KEY_NAME = "ngo_name";


    private String KEY_NO = "number";

    private String KEY_ADD = "address";

    private String KEY_DESC = "description";





    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            showFileChooser();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ngo);
        requestPermission();
        buttonChoose = (TextView) findViewById(R.id.btnupload);
        signup = (Button) findViewById(R.id.Toregister);
        Email = (TextView) findViewById(R.id.email);
        Ngo_name = (EditText) findViewById(R.id.ngo_name);
        Number = (EditText) findViewById(R.id.number);
        Address = (EditText) findViewById(R.id.address);
       Desc = (EditText) findViewById(R.id.desc);
        imageView = (ImageView) findViewById(R.id.imageview);

        context =AddNgo.this;
        pref = new SessionManager(this);
        //sessionManager = new SessionNgo(this);
        extras = getIntent().getExtras();


        pref.checkLogin();

        HashMap<String, String> user = pref.getUserDetails();
        String email = user.get(pref.KEY_EMAIL);


        Email.setText(email);

        buttonChoose.setOnClickListener(this);
        signup.setOnClickListener(this);
    }
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        } else {
            showFileChooser();
        }
    }


    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage() {


        String image = getStringImage(bitmap);
        final String ngo_name = Ngo_name.getText().toString().trim();


        final String number = Number.getText().toString().trim();


        final String address = Address.getText().toString().trim();
        final String description = Desc.getText().toString().trim();
        HashMap<String, String> user = pref.getUserDetails();
        final String email = user.get(pref.KEY_EMAIL);



        Ngo_name.getText().clear();
        Number.getText().clear();
        Address.getText().clear();
        Desc.getText().clear();


        if (ngo_name.length() == 0) {
            Toast.makeText(AddNgo.this, "Please Enter Ngo name", Toast.LENGTH_LONG).show();
        } else {
            if (!isValidNumber(number)) {
                Toast.makeText(AddNgo.this, "Please Enter lastname", Toast.LENGTH_LONG).show();

            }   else {
                            if (address.length() == 0) {
                                Toast.makeText(AddNgo.this, "Please Enter Assress", Toast.LENGTH_LONG).show();
                            } else {

                                if (description.length() == 0) {
                                    Toast.makeText(AddNgo.this, "Please Enter description", Toast.LENGTH_LONG).show();
                                } else {

                                    registerUser(image,email, ngo_name, number, address, description);

                                }

                            }
                        }
                    }

                }



    public void registerUser(final String image,final String email, final String ngo_name,
                             final String number,final String address,final  String description) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
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
                            Intent ver = new Intent(AddNgo.this, NgoProfile.class);
                            //ver.putExtra("women_id", women_id);
                            //ver.putExtra("mobile", mobile);
                            // ver.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            // dialog.dismiss();
                            startActivity(ver);
                            endActivity();
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
                        Toast.makeText(AddNgo.this, error.toString(), Toast.LENGTH_LONG).show();
                    }

                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                params.put(KEY_IMAGE,image);
                params.put(KEY_EMAIL,email);
                params.put(KEY_NAME,ngo_name);
                params.put(KEY_NO,number);
                params.put(KEY_ADD,address);
                params.put(KEY_DESC,description);



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

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }






    private boolean isValidNumber(String number) {
        if (number != null && number.length()==10) {
            return true;
        }
        return false;
    }


    @Override
    public void onClick(View v) {

        if (v == buttonChoose) {
            showFileChooser();
        }

        if (v == signup) {
            uploadImage();
        }


    }


    public void endActivity() {
        this.finish();
    }
}


