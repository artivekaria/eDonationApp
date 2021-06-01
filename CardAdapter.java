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
 * Created by dc on 18-Jan-18.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(SuperHero item);
    }


    private ImageLoader imageLoader;
    private Context context;

    private final OnItemClickListener listener;


    //List of superHeroes
    List<SuperHero> superHeroes;


    public CardAdapter(List<SuperHero> superHeroes, Context context, OnItemClickListener listener) {
        super();
        //Getting all the superheroes
        this.superHeroes = superHeroes;
        this.context = context;
       this.listener = listener;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_ngo, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


      holder.bind(superHeroes.get(position), listener);

        SuperHero superHero = superHeroes.get(position);

        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(superHero.getImage(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
        holder.imageView.setImageUrl(superHero.getImage(), imageLoader);
        holder.ngo_name.setText(superHero.getNgo_name());
        holder.email.setText(superHero.getEmail());
        holder.number.setText(superHero.getNumber());

        holder.address.setText(superHero.getAddress());
        holder.desc.setText(superHero.getDescription());



    }

    @Override
    public int getItemCount() {
        return superHeroes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView ngo_name;
        public TextView email;
        public TextView number;
        public TextView address;
        public TextView desc;

        public NetworkImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);


            ngo_name = (TextView) itemView.findViewById(R.id.ngo_name);

            number = (TextView) itemView.findViewById(R.id.number);
            address = (TextView) itemView.findViewById(R.id.address);
            desc = (TextView) itemView.findViewById(R.id.desc);
            email = (TextView) itemView.findViewById(R.id.email);
            imageView = (NetworkImageView) itemView.findViewById(R.id.imageView);

        }

        public void bind(final SuperHero item, final OnItemClickListener listener) {
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


