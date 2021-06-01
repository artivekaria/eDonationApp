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
 * Created by dc on 19-Jan-18.
 */
public class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.ViewHolder>  {



    public interface OnItemClickListener {
        void onItemClick(Heros item);
    }

    private ImageLoader imageLoader;
    private Context context;


    private final OnItemClickListener listener;


    //List of superHeroes
    List<Heros> superHeroes;


    public DonorAdapter(List<Heros> superHeroes, Context context,DonorAdapter.OnItemClickListener listener) {
        super();
        //Getting all the superheroes
        this.superHeroes = superHeroes;
        this.context = context;
       this.listener = listener;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.donor_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


       holder.bind(superHeroes.get(position), listener);

        Heros superHero = superHeroes.get(position);

      // imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
       // imageLoader.get(superHero.getImage(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
       // holder.imageView.setImageUrl(superHero.getImage(), imageLoader);
        holder.ngo_name.setText(superHero.getNgo_name());
        holder.donor_name.setText(superHero.getDonor_name());
        holder.donor_email.setText(superHero.getDonor_email());
        holder.donet_money.setText(superHero.getDonet_money());
        holder.address.setText(superHero.getAddress());
        holder.number.setText(superHero.getNumber());



    }

    @Override
    public int getItemCount() {
        return superHeroes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView ngo_name;
        public TextView donor_name;
        public TextView donor_email;
        public TextView donet_money;
        public TextView address;
        public TextView number;

        public NetworkImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);


            ngo_name = (TextView) itemView.findViewById(R.id.ngo_name);

            donor_name = (TextView) itemView.findViewById(R.id.donor_name);
            address = (TextView) itemView.findViewById(R.id.address);
            number = (TextView) itemView.findViewById(R.id.number);
            donor_email = (TextView) itemView.findViewById(R.id.donor_email);

            donet_money = (TextView) itemView.findViewById(R.id.donet_money);

        }

        public void bind(final Heros item, final OnItemClickListener listener) {
            //name.setText(item.name);
            // Picasso.with(itemView.getContext()).load(item.imageUrl).into(image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

        }


    }
}




