package com.example.go_mmt.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.go_mmt.Model.Flight;
import com.example.go_mmt.Repository.ApiCall;

import java.util.List;


public class FlightViewModel extends ViewModel {
    private MutableLiveData<List<Flight>> flights;

    public LiveData<List<Flight>> getFlights(){
        if(flights==null){
            flights= new MutableLiveData<>();
            fetchFlights();
        }
        return flights;
    }

    private void fetchFlights() {
        flights=ApiCall.fetchFlights();
    }

}
