package com.wayne;
import java.util.List;
import java.io.IOException;


// this interface is an example of the Dependency Inversion Principle. This is the abstract
// class that other details will implement

public interface ChillSpotRepository {
    void save(ChillSpot chillSpot) throws IOException, InterruptedException;  // saves a Chill Spot
    List<ChillSpot> showAll() throws IOException;       // shows all Chill Spots
}

