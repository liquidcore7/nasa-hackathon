package com.knockoutsong.server.audio;

import com.sun.media.sound.FFT;

import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;


/**
 * Created by devnull on 20/10/18.
 */
public class AudioProcessing {
    final static ClassLoader classLoader = ClassLoader.getSystemClassLoader();

    public static void main(String[] args) {
        String path = "audio/john_lennon_imagine.wav";
        process(path);
    }

    public static void process(String path) {
        try {
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            File file = new File(loader.getResource(path).getFile());

            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            AudioFormat format = stream.getFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);


            byte buffer[] = toByteArray(stream);
            double pitches[] = getFFTData(buffer);
            Arrays.stream(pitches).forEach(x -> System.out.println(normalize(x)));


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    private static byte[] toByteArray(InputStream inputStream) throws IOException{
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int len;

        while ((len = inputStream.read(buffer))!=-1){
            os.write(buffer, 0, len);
        }


        return buffer;
    }

    public static double[] getFFTData(byte[] buffer){
        int sampleSize = buffer.length;

        double[] amplitudes = new double[buffer.length];
        for (int i = 0; i < buffer.length;i++) {
            amplitudes[i] = (double)buffer[i];
        }
        FFT fft = new FFT(sampleSize / 2, -1);
        fft.transform(amplitudes);
        int indexSize = sampleSize / 2;

        int positiveSize = indexSize / 2;

        double[] mag = new double[positiveSize];
        for (int i = 0; i < indexSize; i += 2) {
            mag[i / 2] = Math.sqrt(amplitudes[i] * amplitudes[i] + amplitudes[i + 1] * amplitudes[i + 1]);
        }

        return mag;
    }
    private static double normalize(double x){ // sigmoid
        System.out.println(x);
        return 1f/(1f + Math.pow(Math.E, (-x/100000)));
    }
}
