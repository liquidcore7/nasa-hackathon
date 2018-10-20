package com.knockoutsong.server.closeapproaches;

import java.text.ParseException;
import java.util.Random;

public class ApproachEntityAdapter {
    private static Random random = new Random();

    private ApproachEntity entity;
    private double relativeTime;
    private boolean aboveZero;

    public ApproachEntityAdapter(ApproachEntity entity, int songDuration) throws ParseException {
        this.entity = entity;
        // per 0.3 seconds
        double yearsPerPeriod = songDuration / 100.0;
        this.relativeTime = entity.getYearsFromEpoch() / yearsPerPeriod;
        this.aboveZero = random.nextBoolean();
    }

    public ApproachEntity getEntity() {
        return entity;
    }

    public double getRelativeTime() {
        return relativeTime;
    }

    public boolean isAboveZero() {
        return aboveZero;
    }
}
