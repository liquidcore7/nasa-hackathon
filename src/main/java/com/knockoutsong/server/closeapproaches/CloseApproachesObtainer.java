package com.knockoutsong.server.closeapproaches;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Stream;


public class CloseApproachesObtainer {
    private static final String nasaUrl = "https://ssd-api.jpl.nasa.gov/cad.api?dist-max=0.5&date-min=1900-01-01&date-max=2000-01-01&body=ALL";
    private String approaches;

    public CloseApproachesObtainer() throws IOException {
        URLConnection connection = new URL(nasaUrl).openConnection();
        connection.setReadTimeout(20000);
        approaches = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        ).readLine();
    }

    Stream<ApproachEntity> toStream() {
        
    }
}
