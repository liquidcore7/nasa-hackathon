package com.knockoutsong.server.audio;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AudioProcessingTest extends Assert {

    private static final String path = "audio/john_lennon_imagine.wav";


    @Test
    public void testAudio() {
        AudioProcessing.process(path);
        assertTrue(true);
    }
}
