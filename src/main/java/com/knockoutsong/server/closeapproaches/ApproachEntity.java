package com.knockoutsong.server.closeapproaches;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApproachEntity {
    private static SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy");
    private static double secondsPerYear = 365.25 * 24 * 60 * 60;
    private String name;
    private double distance;
    private String prettyTime;

    public ApproachEntity(String name, double distance, String time) {
        this.name = name;
        this.distance = distance;
        this.prettyTime = time;
    }

    public double getYearsFromEpoch() throws ParseException {
        return ( format.parse(prettyTime).toInstant().getEpochSecond() - format.parse("1900-01-01").toInstant().getEpochSecond() ) / secondsPerYear;
    }
}
