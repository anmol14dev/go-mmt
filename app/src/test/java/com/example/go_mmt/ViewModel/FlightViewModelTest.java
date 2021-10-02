package com.example.go_mmt.ViewModel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.go_mmt.Model.Flight;
import com.example.go_mmt.Model.FlightModel;
import com.example.go_mmt.Repository.ApiCall;
import com.example.go_mmt.Repository.ApiClient;
import com.example.go_mmt.Repository.FlightService;


import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

import static org.mockito.Mockito.when;

public class FlightViewModelTest extends TestCase {
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
    @Mock
    ApiCall apiCall;
    private FlightViewModel flightViewModel;
    @Mock
    private static FlightService flightService;
    @Mock
    Observer<List<Flight>> observer;
    @Mock
    LifecycleOwner lifecycleOwner;
    @Mock
    Call<FlightModel> call;
    Lifecycle lifecycle;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        flightService= ApiClient.getClient().create(FlightService.class);
        call = flightService.listFlights();
        lifecycle = new LifecycleRegistry(lifecycleOwner);
        flightViewModel=new FlightViewModel();
        flightViewModel.getFlights().observeForever(observer); ;
    }
    @Test
    public void testNull() throws InterruptedException {
        when(apiCall.fetchFlights()).thenReturn(null);
        assertNotNull(flightViewModel.getFlights());
    }

    @After
    public void tearDown() throws Exception {
        apiCall=null;
        flightViewModel=null;
    }
}