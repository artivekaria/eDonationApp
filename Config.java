package com.example.nik.fcnc;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Nik on 10/7/2016.
 */
public class Config {


    private static Config mInstance;
    private RequestQueue reqQue;
    private static Context mCtx;

    private Config(Context context) {
        this.mCtx = context;
        reqQue = getReqQue();
    }

    public RequestQueue getReqQue() {
        if (reqQue == null) {
            reqQue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return reqQue;
    }

    public static synchronized Config getmInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Config(context);
        }
        return mInstance;
    }

    public <T> void addReq(Request<T> request) {
        reqQue.add(request);
    }
}


