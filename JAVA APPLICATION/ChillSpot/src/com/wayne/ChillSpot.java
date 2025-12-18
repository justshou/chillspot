package com.wayne;
import java.util.*;

// this file initiates all variables and validates them. all variables
// are final as they are not meant to be changed

public class ChillSpot {

    // initializing variables that user will input

    private final String name;
    private final String building;
    private final String floor;
    private final int seats;
    private final String imgURL;


    // getters for variables


    public String getName() {
        return name;
    }

    public String getBuilding() {
        return building;
    }

    public String getFloor() {
        return floor;
    }

    public int getSeats() {
        return seats;
    }

    public String getimgURL() {
        return imgURL;
    }

    public ChillSpot(String name, String building, String floor, int seats, String imgURL) {

        // constructor sets variable data

        this.name = name;
        this.building = building;
        this.floor = floor;
        this.seats = seats;
        this.imgURL = imgURL;

        // calls validate input to check if  name, building, and imageURL are blank
        validateInput();
    }


    // function to make sure all variables are filled out
    public void validateInput(){
        if (name.isBlank()) throw new IllegalArgumentException("Name can't be blank");
        if (building.isBlank()) throw new IllegalArgumentException("Building can't be blank");
        if (floor.isBlank()) throw new IllegalArgumentException("Floor can't be blank");
        if (seats <= 0) throw new IllegalArgumentException("Seats must be greater than 0");
        if (imgURL.isBlank()) throw new IllegalArgumentException("Image can't be blank");
    }


}
