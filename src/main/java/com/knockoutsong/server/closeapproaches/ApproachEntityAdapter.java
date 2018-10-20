package com.knockoutsong.server.closeapproaches;

import java.text.ParseException;

public class ApproachEntityAdapter {
    private ApproachEntity entity;
    private double relativeTime;

    public ApproachEntityAdapter(ApproachEntity entity, int songDuration) throws ParseException {
        this.entity = entity;
        this.relativeTime = 100.0 * entity.getYearsFromEpoch() / (3 * songDuration);
    }

    public ApproachEntity getEntity() {
        return entity;
    }

    public double getRelativeTime() {
        return relativeTime;
    }
}
