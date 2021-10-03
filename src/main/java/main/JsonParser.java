package main;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    public List<TicketData> parse(String filename) {
        JSONParser parser = new JSONParser();
        List<TicketData> ticketsList = new ArrayList<>();
        try (FileReader reader = new FileReader(filename)) {
            reader.skip(1); // necessary because of inability of simple json parser digest start file symbol
            JSONObject ticketsMain = (JSONObject) parser.parse(reader);
            JSONArray tickets = (JSONArray) ticketsMain.get("tickets");
            for (Object i : tickets) {
                JSONObject ticketsData = (JSONObject) i;
                String departureDate = (String) ticketsData.get("departure_date");
                String departureTime = (String) ticketsData.get("departure_time");
                String arrivalDate = (String) ticketsData.get("arrival_date");
                String arrivalTime = (String) ticketsData.get("arrival_time");

                TicketData ticketData = new TicketData(departureDate,departureTime, arrivalDate, arrivalTime);
                ticketsList.add(ticketData);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File with specified path does not exist: " + filename);
            System.exit(1);
        } catch (IOException | ParseException ex) {
            System.out.println("Invalid format of the specified file " + filename);
            System.exit(1);
        }

        return ticketsList;
    }
}
