package main;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.format.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class FlyStatsUtils {
    private static final DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yy HH:mm");

    public static Duration getDuration(TicketData data) {
        String departureDate = data.departureDate + " " + data.departureTime;
        DateTime depDateTime = DateTime.parse(departureDate, formatter);
        String arrivalDate = data.arrivalDate + " " + data.arrivalTime;
        DateTime arrDateTime = DateTime.parse(arrivalDate, formatter);
        return new Duration(depDateTime, arrDateTime);
    }

    public static Period getAverage(List<TicketData> ticketsData) {
        long sum = 0;
        for (TicketData i : ticketsData) {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yy HH:mm");
            String departureDate = i.departureDate + " " + i.departureTime;
            DateTime depDateTime = DateTime.parse(departureDate, formatter);
            String arrivalDate = i.arrivalDate + " " + i.arrivalTime;
            DateTime arrDateTime = DateTime.parse(arrivalDate, formatter);
            Duration diff = new Period(depDateTime, arrDateTime).toStandardDuration();
            sum += diff.getMillis();
        }
        long averageMs = sum / ticketsData.size();
        return new Period(averageMs);
    }

    public static Period get90Percentile(List<TicketData> ticketsData) {
        int per90 = ticketsData.size() * 9 / 10;
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yy HH:mm");
        String departureDate = ticketsData.get(per90 - 1).departureDate + " " + ticketsData.get(per90 - 1).departureTime;
        DateTime depDateTime = DateTime.parse(departureDate, formatter);
        String arrivalDate = ticketsData.get(per90 - 1).arrivalDate + " " + ticketsData.get(per90 - 1).arrivalTime;
        DateTime arrDateTime = DateTime.parse(arrivalDate, formatter);
        return new Period(depDateTime, arrDateTime);
    }

    public static List<TicketData> sort(List<TicketData> ticketsData) {
        return ticketsData.stream().sorted(
            Comparator.comparingLong(ticket -> getDuration(ticket).getMillis())
        ).collect(Collectors.toList());
    }

}
