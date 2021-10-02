package com.example.go_mmt.Repository;

import com.example.go_mmt.Model.Flight;
import com.example.go_mmt.Model.FlightModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FlightService {

    @GET("v3/f1cb8ae1-42c7-4ac6-9b63-2f5f055648d5")
    Call<FlightModel> listFlights();
}
