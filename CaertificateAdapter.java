package com.example.nik.fcnc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by Admin on 14-Apr-18.
 */

public class CaertificateAdapter extends RecyclerView.Adapter<CaertificateAdapter.ViewHolder>{



    private ImageLoader imageLoader;
    private Context context;

   // private final CaertificateAdapter.OnItemClickListener listener;


    //List of superHeroes
    List<CertificateHero> superHeroes;


    public CaertificateAdapter(List<CertificateHero> superHeroes, Context context) {
        super();
        //Getting all the superheroes
        this.superHeroes = superHeroes;
        this.context = context;
     //   this.listener = listener;


    }

    @Override
    public CaertificateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.certificate, parent, false);
        CaertificateAdapter.ViewHolder viewHolder = new CaertificateAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CaertificateAdapter.ViewHolder holder, int position) {


        //holder.bind(superHeroes.get(position), listener);

        CertificateHero superHero = superHeroes.get(position);

        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        holder.ngo_name.setText(superHero.getNgo_name());
        holder.donor_name.setText(superHero.getDonor_name());
        holder.donet_money.setText(superHero.getDonet_money());

        holder.address.setText(superHero.getAddress());
        holder.edesc.setText(superHero.getText());



    }

    @Override
    public int getItemCount() {
        return superHeroes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView ngo_name;
        public TextView donor_name;
        public TextView donet_money;
        public TextView address;
        public TextView edesc;




        public ViewHolder(View itemView) {
            super(itemView);


            ngo_name = (TextView) itemView.findViewById(R.id.ngo_name);

            donor_name = (TextView) itemView.findViewById(R.id.donor_name);

            edesc= (TextView) itemView.findViewById(R.id.buttonPay);
            address = (TextView) itemView.findViewById(R.id.address);
            donet_money = (TextView) itemView.findViewById(R.id.donet_money);
          //  imageView = (NetworkImageView) itemView.findViewById(R.id.imageView);

        }




    }
}