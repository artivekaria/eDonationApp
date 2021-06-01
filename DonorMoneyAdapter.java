package com.example.nik.fcnc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dc on 23-Jan-18.
 */
public class DonorMoneyAdapter  extends ArrayAdapter{

    List list = new ArrayList();

    public DonorMoneyAdapter(Context context, int resource) {

        super(context, resource);
    }



    @Override
    public void add(Object object) {


        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }




    static class DataHandler {
        TextView ngo_name;
        TextView donor_name;
        TextView donor_email;
        TextView payment_mode;
        TextView donet_money;
        TextView address;
        TextView number;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        DataHandler handler = new DataHandler();


        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.view_donor_money, parent, false);

        handler.ngo_name= (TextView) row.findViewById(R.id.ngo_name);
        handler.donor_name= (TextView) row.findViewById(R.id.donor_name);
        handler.donor_email= (TextView) row.findViewById(R.id.donor_email);
        handler.payment_mode= (TextView) row.findViewById(R.id.payment_mode);
        handler.donet_money= (TextView) row.findViewById(R.id.donet_money);
        handler.address= (TextView) row.findViewById(R.id.address);
        handler.number= (TextView) row.findViewById(R.id.number);




        row.setTag(handler);


        DonorMoneyHero contact;
        contact = (DonorMoneyHero) this.getItem(position);

        handler.ngo_name.setText(contact.getNgo_name());
        handler.donor_name.setText(contact.getDonor_name());
        handler.donor_email.setText(contact.getDonor_email());
        handler.payment_mode.setText(contact.getPayment_mode());
        handler.donet_money.setText(contact.getDonet_money());
        handler.number.setText(contact.getNumber());
        handler.address.setText(contact.getAddress());


        return row;
    }


}