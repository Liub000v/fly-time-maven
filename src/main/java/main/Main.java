package main;

import org.joda.time.format.PeriodFormat;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        JsonParser parser = new JsonParser();
        if (args.length == 0) {
            System.out.println("You must pass file path to *.json");
            System.exit(1);
        }
        List<TicketData> ticketsData = parser.parse(args[0]);
        List<TicketData> sortedData = FlyStatsUtils.sort(ticketsData);
        System.out.println("Average flight time " + PeriodFormat.getDefault().print(FlyStatsUtils.getAverage(ticketsData)));
        System.out.println("90 percentile flight time " + PeriodFormat.getDefault().print(FlyStatsUtils.get90Percentile(sortedData)));
    }
}
