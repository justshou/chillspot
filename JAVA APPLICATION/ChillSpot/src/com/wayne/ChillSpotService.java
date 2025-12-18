package com.wayne;
import java.io.IOException;


// This class serves as the main way to add a spot to the repository

public class ChillSpotService {


    // initialize repo object and set it with constructor

    private final ChillSpotRepository repo;

    public ChillSpotService(ChillSpotRepository repo){
        this.repo = repo;
    }

    // validates to make sure chillSpot inputs are okay then saves it to the repo

    public void addSpot(ChillSpot chillSpot) throws IOException, InterruptedException {
        chillSpot.validateInput();
        repo.save(chillSpot);
    }
}
