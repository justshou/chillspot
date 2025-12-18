package com.wayne;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonChillSpotRepository implements ChillSpotRepository {

    private final Path file;
    private final ObjectMapper mapper;

    public JsonChillSpotRepository(Path file){

        // constructor stores json file path and creates an ObjectMapper object for json

        this.file = file;
        this.mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    }


    // uses save class

    @Override
    public void save(ChillSpot chillSpot) throws IOException {
        List<ChillSpot> spots = showAll(); // loads existing spots
        spots.add(chillSpot);              // makes a new spot


        Path parent = file.getParent();    // makes sure folder exists
        if (parent != null){
            Files.createDirectories(parent);
        }


        // writes the list to Json file

        String json = mapper.writeValueAsString(spots);
        Files.writeString(file, json, StandardCharsets.UTF_8);
    }




    @Override
    public List<ChillSpot> showAll() throws IOException {


        // checks if file exists
        // if there is no file return an empty list

        if (!Files.exists(file)){
            return new ArrayList<>();
        }


        // reads content of files

        String json = Files.readString(file, StandardCharsets.UTF_8);


        // handles empty files

        if (json.isBlank()){
            return new ArrayList<>();
        }


        // converts json array into list

        return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(
                List.class, ChillSpot.class)
        );

    }
}


