package com.knockoutsong.server.closeapproaches;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApproachEntity {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static double secondsPerYear = 365.25 * 24 * 60 * 60;


    private String name;
    private double distance;
    private String prettyTime;
    private String planet;

    public ApproachEntity(String name, double distance, String time, String planet) {
        this.name = name;
        this.distance = distance;
        this.prettyTime = time;
        this.planet = planet;
    }

    public double getYearsFromEpoch() throws ParseException {
        return ( format.parse(prettyTime).toInstant().getEpochSecond() -
                    format.parse("1900-01-01 00:00").toInstant().getEpochSecond()
        ) / secondsPerYear;
    }

    public String getName() {
        return name;
    }

    public double getDistance() {
        return distance;
    }

    public String getPlanet() {
        return planet;
    }
}
