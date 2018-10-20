package com.knockoutsong.server.audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import java.io.IOException;

/**
 * Created by devnull on 20/10/18.
 */
public class PCMSigned8Bit extends AbstractSignedLevelConverter {

    PCMSigned8Bit(AudioFormat sourceFormat)
    {
        super(sourceFormat);
    }

    public double convertToLevel(byte[] chunk) throws IOException
    {
        if(chunk.length != getRequiredChunkByteSize())
            return -1;

        AudioInputStream ais = convert(chunk);
        ais.read(chunk, 0, chunk.length);

        return (double)chunk[0];
    }

}
