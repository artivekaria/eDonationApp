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
 * Created by dc on 22-Jan-18.
 */
public class MaterialAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public MaterialAdapter(Context context, int resource) {

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
        TextView email;
        TextView quantity;
        TextView material;
        TextView des;
        TextView jdate;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        DataHandler handler = new DataHandler();


        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.view_material, parent, false);

        handler.ngo_name= (TextView) row.findViewById(R.id.ngo_name);
        handler.email= (TextView) row.findViewById(R.id.email);
        handler.material= (TextView) row.findViewById(R.id.material);
        handler.quantity= (TextView) row.findViewById(R.id.quantity);
        handler.des= (TextView) row.findViewById(R.id.des);
        handler.jdate= (TextView) row.findViewById(R.id.jdate);





        row.setTag(handler);


        MaterialHero contact;
        contact = (MaterialHero) this.getItem(position);

        handler.ngo_name.setText(contact.getNgo_name());
        handler.email.setText(contact.getEmail());
        handler.material.setText(contact.getMaterial());
        handler.quantity.setText(contact.getQuantity());
        handler.des.setText(contact.getDes());
        handler.jdate.setText(contact.getJdate());


        return row;
    }

}



