package ap.assignment2;

import java.util.*;

public class Attraction {
    /*
     * Attraction class to store all the attributes, getters and setters for the
     * attractions
     */
    private String name;
    private String description;
    private static int attractionID = 1000;
    private int ticketsSold = 0;
    private String status;
    private int id;
    private double basicCost;

    Attraction() {
        this.name = "";
        this.description = "";
    }

    Attraction(String name, String detail) {
        this.name = name;
        this.description = detail;
        this.id = ++attractionID;
        this.status = "OPEN";
    }

    public int getId() {
        return id;
    }

    public double getBasicCost() {
        return basicCost;
    }

    public void setBasicCost(double d) {
        this.basicCost = d;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold += ticketsSold;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Attraction ID: " + this.id + "\nAttraction: " + this.name + "\nDescription: " + this.description;
    }
}

class AttractionComparator implements Comparator<Attraction> {
    /*
     * Comparator class to sort the ArrayList of Attractions in descending order of
     * tickets sold to compare the popularity
     */
    @Override
    public int compare(Attraction o1, Attraction o2) {
        return Integer.compare(o2.getTicketsSold(), o1.getTicketsSold());
    }
}