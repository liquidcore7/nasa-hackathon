package com.knockoutsong.server.closeapproaches;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class ApproachEntity {
    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm", Locale.ENGLISH);
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
        LocalDate diff = LocalDate.parse(prettyTime, format).minusYears(1900);
        return diff.getYear() + diff.getDayOfYear() / 365.25;
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
