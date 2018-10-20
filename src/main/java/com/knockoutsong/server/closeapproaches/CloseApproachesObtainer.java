package com.knockoutsong.server.closeapproaches;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;


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

    public List<ApproachEntity> toList() {
        JsonParser parser = new JsonParser();
        JsonObject globalBody = parser.parse(approaches).getAsJsonObject();
        JsonArray data = globalBody.getAsJsonArray("data");

        LinkedList<ApproachEntity> entities = new LinkedList<>();
        for (JsonElement e : data) {
            JsonArray content = e.getAsJsonArray();

            ApproachEntity entity = new ApproachEntity(
                    content.get(0).getAsString(),   // about
                    Double.parseDouble( content.get(5).getAsString() ),   // min distance
                    content.get(3).getAsString(),   // date
                    content.get(10).getAsString()   // planet
            );

            if (entity != null) {
                entities.addLast(entity);
            }
        }
        return entities;
    }
}
