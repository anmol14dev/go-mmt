package com.example.go_mmt.Model;

import androidx.annotation.NonNull;

public class Flight {

    private String source;
    private String destination;
    private String Departure;
    private String Arrival;
    private String ticketPrice;
    private String Airline;
    private String logo;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return Departure;
    }

    public void setDeparture(String departure) {
        this.Departure = departure;
    }

    public String getArrival() {
        return Arrival;
    }

    public void setArrival(String arrival) {
        this.Arrival = arrival;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getAirline() {
        return Airline;
    }

    public void setAirline(String airline) {
        this.Airline = airline;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "{" +
                "source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", departure='" + Departure + '\'' +
                ", arrival='" + Arrival + '\'' +
                ", ticketPrice='" + ticketPrice + '\'' +
                ", airline='" + Airline + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}