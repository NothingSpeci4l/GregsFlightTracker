package com.gregwll.flighttracker.api.simbrief;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SimbriefAPI {

    public static String fetchFlightData(String username) throws Exception{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.simbrief.com/api/xml.fetcher.php?username=" + username + "&json=1"))
                .GET()
                .build();
        HttpResponse<String> reponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        return reponse.body();
    }
}
