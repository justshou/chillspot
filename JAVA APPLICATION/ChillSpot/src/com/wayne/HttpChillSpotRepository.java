package com.wayne;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


// This class is responsible for sending data to the backend of our website
// This class follows the Single Responsibility Principle, as it only handles
// HTTP communication, and the Dependency Inversion Principle, as it uses the
// abstractions created by ChillSpotRepository

public class HttpChillSpotRepository implements ChillSpotRepository{


    // initializing variables

    private final URI endpoint;
    private final HttpClient client;
    private final ObjectMapper mapper;


    // constructor takes in an endpoint URL and converts it into a URI, makes a new HTTP client,
    // and creates an object for the Json serializer

    public HttpChillSpotRepository(String endpointUrl) {
        this.endpoint = URI.create(endpointUrl);
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }



    // save class implemented from abstract class

    @Override
    public void save(ChillSpot chillSpot) throws IOException, InterruptedException {

        // serializes the spot to Json

        String jsonBody = mapper.writeValueAsString(chillSpot);


        // creates post request with the Chill Spot data and a header identifying the contents

        HttpRequest request = HttpRequest.newBuilder(endpoint)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();


        // Sends request to website, and if no response is received, throw exception

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("Post failed: " + response.statusCode() + " -- " + response.body());
        }
    }



    // This function is not implemented, but is required by the abstract class to be present in
    // this implementation

    @Override
    public List<ChillSpot> showAll() throws IOException {
        throw new UnsupportedOperationException("showAll() not implemented for HTTP repository yet.");
    }

}
