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
 * Created by dc on 01-May-18.
 */
public class EventAdapter  extends RecyclerView.Adapter<EventAdapter.ViewHolder> {



    private ImageLoader imageLoader;
    private Context context;

  //  private final OnItemClickListener listener;


    //List of superHeroes
    List<EventHero> superHeroes;


    public EventAdapter(List<EventHero> superHeroes, Context context) {
        super();
        //Getting all the superheroes
        this.superHeroes = superHeroes;
        this.context = context;
       // this.listener = listener;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


       // holder.bind(superHeroes.get(position), listener);

        EventHero superHero = superHeroes.get(position);

        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(superHero.getImage(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
        holder.imageView.setImageUrl(superHero.getImage(), imageLoader);
        holder.ngo_name.setText(superHero.getNgo_name());
        holder.event_name.setText(superHero.getEvent_name());
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
        public TextView event_name;
        public TextView email;
        public TextView number;
        public TextView address;
        public TextView desc;

        public NetworkImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);


            ngo_name = (TextView) itemView.findViewById(R.id.ngo_name);
            event_name = (TextView) itemView.findViewById(R.id.event_name);

            number = (TextView) itemView.findViewById(R.id.number);
            address = (TextView) itemView.findViewById(R.id.address);
            desc = (TextView) itemView.findViewById(R.id.desc);
            email = (TextView) itemView.findViewById(R.id.email);
            imageView = (NetworkImageView) itemView.findViewById(R.id.imageView);

        }





    }
}


