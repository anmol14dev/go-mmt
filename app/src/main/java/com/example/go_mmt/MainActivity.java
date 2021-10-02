package com.example.go_mmt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.go_mmt.Adapter.FlightAdapter;
import com.example.go_mmt.Model.Flight;
import com.example.go_mmt.Model.FlightModel;
import com.example.go_mmt.Repository.ApiClient;
import com.example.go_mmt.Repository.FlightService;
import com.example.go_mmt.ViewModel.FlightViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private FlightViewModel flightViewModel;
    private RecyclerView recyclerView;
    private FloatingActionButton priceButton;
    private FloatingActionButton departureButton;
    private FloatingActionButton sortChoiceButton;
    private FlightAdapter adapter;
    private Animation fromBottom;
    private Animation toBottom;
    private boolean closed;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        closed=true;
        findView();
        addListeners();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter= new FlightAdapter(this);
        recyclerView.setAdapter(adapter);
        flightViewModel= new ViewModelProvider(this).get(FlightViewModel.class);
        flightViewModel.getFlights().observe(this, new Observer<List<Flight>>() {
            @Override
            public void onChanged(List<Flight> flights) {
                adapter.setFlights(flights);
                progressBar.setVisibility(View.GONE);
            }
        });
    }
    private void findView(){
        recyclerView=findViewById(R.id.recycler_view);
        priceButton=findViewById(R.id.price_sort);
        departureButton=findViewById(R.id.depart_sort);
        sortChoiceButton=findViewById(R.id.sort_choice);
        toBottom=AnimationUtils.loadAnimation(this,R.anim.to_bottom_anim);
        fromBottom=AnimationUtils.loadAnimation(this,R.anim.from_bottom_anim);
        progressBar=findViewById(R.id.progress_bar);
    }
    private void addListeners(){
        sortChoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibility();
                setAnimations();
                closed=!closed;
            }
        });
        priceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibility();
                setAnimations();
                closed=true;
                sortByPrice();
            }
        });
        departureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibility();
                setAnimations();
                closed=true;
                sortByDeparture();
            }
        });
    }
    private void setVisibility(){
        if(closed){
            priceButton.setVisibility(View.VISIBLE);
            departureButton.setVisibility(View.VISIBLE);
        }
        else{
            priceButton.setVisibility(View.GONE);
            departureButton.setVisibility(View.GONE);
        }

    }

    private void setAnimations(){
        if(closed){
            priceButton.startAnimation(fromBottom);
            departureButton.startAnimation(fromBottom);

        }
        else{
            priceButton.startAnimation(toBottom);
            departureButton.startAnimation(toBottom);
        }

    }
    private void sortByPrice() {
        Collections.sort(adapter.getFlights(), new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                if(Integer.parseInt(o1.getTicketPrice())<Integer.parseInt(o2.getTicketPrice()))
                    return -1;
                else if((Integer.parseInt(o1.getTicketPrice())>Integer.parseInt(o2.getTicketPrice())))
                    return 1;
                else
                    return 0;
            }
        });
        adapter.notifyDataSetChanged();

    }

    private void sortByDeparture(){
        Collections.sort(adapter.getFlights(), new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                Date departO1=null;
                Date departO2=null;
                try {
                    departO1=dateFormatter(o1.getDeparture());
                    departO2= dateFormatter(o2.getDeparture());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(departO1.before(departO2))
                    return -1;
                else if(departO1.after(departO2))
                    return 1;
                else
                    return 0;
            }
        });
        adapter.notifyDataSetChanged();

    }
    public Date dateFormatter(String str) throws ParseException {
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d=null;
        d=formatter.parse(str);
        return d;
    }
}