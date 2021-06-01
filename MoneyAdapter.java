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
public class MoneyAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public MoneyAdapter(Context context, int resource) {

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
        TextView money;
        TextView des;
        TextView jdate;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        DataHandler handler = new DataHandler();


        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.view_money, parent, false);

        handler.ngo_name= (TextView) row.findViewById(R.id.ngo_name);
        handler.email= (TextView) row.findViewById(R.id.email);
        handler.money= (TextView) row.findViewById(R.id.money);
        handler.des= (TextView) row.findViewById(R.id.des);
        handler.jdate= (TextView) row.findViewById(R.id.jdate);





        row.setTag(handler);


        MoneyHero contact;
        contact = (MoneyHero) this.getItem(position);

        handler.ngo_name.setText(contact.getNgo_name());
        handler.email.setText(contact.getEmail());
        handler.money.setText(contact.getMoney());
        handler.des.setText(contact.getDes());
        handler.jdate.setText(contact.getJdate());


        return row;
    }

}



