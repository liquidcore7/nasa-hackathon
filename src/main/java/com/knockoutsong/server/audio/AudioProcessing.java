package com.knockoutsong.server.audio;

import com.sun.media.sound.FFT;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


/**
 * Created by devnull on 20/10/18.
 */
public class AudioProcessing {

    public static void main(String []args){

        String path = "audio/john_lennon_imagine.wav";
        process(path);



    }

    public static int[] process(String path){
        File file = new File(path);
        Clip clip;
        AudioInputStream stream;
        SourceDataLine sourceLine;
        Mixer mixer;
        try {
            stream = AudioSystem.getAudioInputStream(file);
            AudioFormat format = stream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            Mixer.Info[] mixers = AudioSystem.getMixerInfo();

            sourceLine = AudioSystem.getSourceDataLine(format);

            clip = AudioSystem.getClip();
            clip.open(stream);


            AudioSystem.getTargetDataLine(format).open(format);


            FFT fft = new FFT(1, -1);



            byte[] buffer = new byte[stream.getFormat().getFrameSize()];
            stream.read(buffer, 0, buffer.length);
            try{
                System.out.println(getLevel(stream.getFormat(), buffer));
            }
            catch(Exception e){
                System.out.println("ERROR");
                e.printStackTrace();
            }


    //        System.out.println(clip.getMicrosecondLength());

        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
    public static double getLevel(AudioFormat af, byte[] chunk) throws IOException {
        PCMSigned8Bit converter = new PCMSigned8Bit(af);
        if(chunk.length != converter.getRequiredChunkByteSize())
            return -1;

        AudioInputStream ais = converter.convert(chunk);
        ais.read(chunk, 0, chunk.length);

        long lSum = 0;
        for(int i=0; i<chunk.length; i++)
            lSum = lSum + chunk[i];

        double dAvg = lSum / chunk.length;
        double sumMeanSquare = 0d;

        for(int j=0; j<chunk.length; j++)

            sumMeanSquare = sumMeanSquare + Math.pow(chunk[j] - dAvg, 2d);

        double averageMeanSquare = sumMeanSquare / chunk.length;

        return (Math.pow(averageMeanSquare,0.5d));
    }




    private double[] getData(Clip clip){
        //#TODO
        return null;
    }
}
