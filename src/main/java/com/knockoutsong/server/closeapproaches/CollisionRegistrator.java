package com.knockoutsong.server.closeapproaches;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class CollisionRegistrator {
    private static double secondsPerPosition = 1/0.3;
    private static double kmToAU = 1.0 / 149597871;

    private static Map<String, Integer> planetRadius = new HashMap<>();
    static {
        planetRadius.put("Merc", 2440);
        planetRadius.put("Venus", 6052);
        planetRadius.put("Earth", 6378);
        planetRadius.put("Mars", 3397);
        planetRadius.put("Juptr", 71492);
        planetRadius.put("Satrn", 60268);
        planetRadius.put("Urnus", 25559);
        planetRadius.put("Neptn", 24766);
        planetRadius.put("Pluto", 1150);
        planetRadius.put("Moon", 1738);
    }

    private List<ApproachEntity> entityList;

    public CollisionRegistrator() {
        try {
            CloseApproachesObtainer obtainer = new CloseApproachesObtainer();
            entityList = obtainer.toList();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean inRange(ApproachEntityAdapter entityAdapter, double systemPos) {
        if (entityAdapter.isAboveZero() && systemPos < 0)
            return false;

        return Math.abs(entityAdapter.getEntity().getDistance() - Math.abs(systemPos))
                < planetRadius.get(entityAdapter.getEntity().getPlanet()) * kmToAU;
    }

    public List<ApproachEntityAdapter> getCollisions(Double[] positions) {
        return entityList.parallelStream()
                .map(ae -> {
                    try {
                        return new ApproachEntityAdapter(ae, (int) (positions.length * secondsPerPosition));
                    } catch (ParseException e) {
                        return null;
                    }
                }).filter(Objects::nonNull)
                .filter(e -> {
                    double systemPosition = positions[Math.round((float) e.getRelativeTime() / 0.3f)];
                    return CollisionRegistrator.inRange(e, systemPosition / 2.0);
                }).collect(Collectors.toList());
    }
}
