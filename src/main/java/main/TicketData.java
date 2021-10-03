package main;

import org.joda.time.Duration;

public class TicketData {
    public String departureDate;
    public String departureTime;
    public String arrivalDate;
    public String arrivalTime;


    public TicketData(String departureDate, String departureTime, String arrivalDate, String arrivalTime){
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
    }
}
