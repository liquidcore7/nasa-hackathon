package com.knockoutsong.server.audio;

//import com.sun.media.sound.FFT;

import com.badlogic.audio.analysis.FFT;

import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by devnull on 20/10/18.
 */
public class AudioProcessing {
    private final static ClassLoader load = ClassLoader.getSystemClassLoader();
    private static int BUFFER_SIZE;
    private final static int STEP = 100000;

    public static void process(String path) {
        try {
            File file = new File(load.getResource(path).getFile());
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            AudioFormat format = stream.getFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);


            System.out.println(clip.getMicrosecondLength());

            BUFFER_SIZE = (int)(clip.getMicrosecondLength()/STEP);
            BUFFER_SIZE += BUFFER_SIZE%8;
            System.out.println(BUFFER_SIZE);

            byte buffer[] = toByteArray(stream);
            double pitches[] = getFFTData(buffer);

            pitches = normalize(pitches);

            Arrays.stream(pitches).forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    private static byte[] toByteArray(InputStream inputStream) throws IOException{
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        int len;

        while ((len = inputStream.read(buffer))!=-1){
            os.write(buffer, 0, len);
        }
        return buffer;
    }

    private static double[] getFFTData(byte[] buffer){

        float[] amplitudes = new float[buffer.length];
        for (int i = 0; i < buffer.length;i++) {
            amplitudes[i] = (float) buffer[i];
        }
        FFT fft = new FFT(BUFFER_SIZE / 2, -1);
        fft.forward(amplitudes);
        int indexSize = BUFFER_SIZE / 2;

        int positiveSize = indexSize / 2;

        double[] mag = new double[positiveSize];
        for (int i = 0; i < indexSize; i += 2) {
            mag[i / 2] = Math.sqrt(amplitudes[i] * amplitudes[i] + amplitudes[i + 1] * amplitudes[i + 1]);
        }

        return mag;
    }
    private static double[] normalize(double x[]){ // linear fucntion
        ArrayList<Double> list = new ArrayList();
        Arrays.stream(x).forEach(list::add);
        double max, avg, sum = 0;
        for (double d: list) {
            sum += d;
        }
        avg = sum/list.size();
        max = list.stream().reduce(0.0, Math::max);
        System.err.println(avg);
        System.err.println(max);

        for (int i = 0; i < list.size()-1; i++) {
            x[i] = (x[i] - avg)/(max);
        }
        return x;
    }
}
