package com.knockoutsong.server.closeapproaches;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.stream.IntStream;

public class CollisionRegistratorTestClass extends Assert {
    private Double[] pseudotrack;
    private static Random random = new Random();
    private CollisionRegistrator registrator = new CollisionRegistrator();

    @Before
    public void fillTrack() {
        pseudotrack = IntStream.rangeClosed(0, 300)
                .mapToDouble(i -> i)
                .flatMap(is -> IntStream.of(1, 2, 3).mapToDouble(i -> random.nextDouble()))
                .boxed()
                .toArray(Double[]::new);
    }

    //public CollisionRegistratorTestClass() {}

    @Test
    public void testCollisions() {
        assertNotNull(registrator.getCollisions(pseudotrack));
    }
}
