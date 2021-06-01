package com.example.nik.fcnc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dc on 23-Jan-18.
 */
public class DonorMaterialAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public DonorMaterialAdapter(Context context, int resource) {

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
        TextView material;
        TextView donet_quantity;
        TextView address;
        TextView number;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        DataHandler handler = new DataHandler();


        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.view_donor_material, parent, false);

        handler.ngo_name= (TextView) row.findViewById(R.id.ngo_name);
        handler.donor_name= (TextView) row.findViewById(R.id.donor_name);
        handler.donor_email= (TextView) row.findViewById(R.id.donor_email);
        handler.material= (TextView) row.findViewById(R.id.material);
        handler.donet_quantity= (TextView) row.findViewById(R.id.quantity);
        handler.address= (TextView) row.findViewById(R.id.address);
        handler.number= (TextView) row.findViewById(R.id.number);




        row.setTag(handler);


        DonorMaterialHero contact;
        contact = (DonorMaterialHero) this.getItem(position);

        handler.ngo_name.setText(contact.getNgo_name());
        handler.donor_name.setText(contact.getDonor_name());
        handler.donor_email.setText(contact.getDonor_email());
        handler.material.setText(contact.getMaterial());
        handler.donet_quantity.setText(contact.getDonet_quantity());
        handler.address.setText(contact.getAddress());
        handler.number.setText(contact.getNumber());

        return row;
    }


}
