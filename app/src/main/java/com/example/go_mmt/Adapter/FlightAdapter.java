package com.example.go_mmt.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.go_mmt.Model.Flight;
import com.example.go_mmt.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder> {
    private List<Flight> flights=new ArrayList<>();
    private Context context;
    public FlightAdapter(Context context){
        this.context=context;
    }
    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flight_item,parent,false);
        return new FlightViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
                Flight currentFlight= flights.get(position);
                Glide.with(context).load(flights.get(position).getLogo()).apply(RequestOptions.centerCropTransform()).into(holder.logo);
                holder.airline.setText(currentFlight.getAirline());
                holder.src.setText(currentFlight.getSource().toUpperCase());
                holder.dest.setText(currentFlight.getDestination().toUpperCase());
                String departure= currentFlight.getDeparture();
                String arrival= currentFlight.getArrival();
                holder.arrival.setText(arrival.substring(arrival.indexOf(' ')+1,arrival.length()));
                holder.duration.setText(calDuration(departure,arrival));
                holder.depart.setText(departure.substring(departure.indexOf(' ')+1,departure.length()));
                holder.price.setText("\u20B9 "+currentFlight.getTicketPrice());
    }

    @Override
    public int getItemCount() {
        return flights.size();
    }
    public void setFlights(List<Flight> flights){
        this.flights=flights;
        notifyDataSetChanged();
    }
    public List<Flight> getFlights(){
        return flights;
    }

    private String calDuration(String departure,String arrival){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d1 =null;
        Date d2 =null;
        try {
            d2=formatter.parse(departure);
            d1=formatter.parse(arrival);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diff=d1.getTime()-d2.getTime();
        long diffHrs=diff/(60*60*1000);
        long diffMins=(diff/(60*1000))%60;
        String duration= diffHrs+ "h"+ " "+ diffMins+ "m";
        return duration;
    }

    class FlightViewHolder extends RecyclerView.ViewHolder {
        private TextView airline;
        private ImageView logo;
        private TextView src;
        private TextView dest;
        private TextView depart;
        private TextView duration;
        private TextView price;
        private TextView arrival;

        public FlightViewHolder(@NonNull View itemView) {
            super(itemView);
            airline=itemView.findViewById(R.id.airline);
            src=itemView.findViewById(R.id.source);
            dest=itemView.findViewById(R.id.destination);
            arrival=itemView.findViewById(R.id.arrival);
            depart=itemView.findViewById(R.id.departure);
            duration=itemView.findViewById(R.id.duration);
            price=itemView.findViewById(R.id.price);
            logo=itemView.findViewById(R.id.logo);
        }
    }

}
