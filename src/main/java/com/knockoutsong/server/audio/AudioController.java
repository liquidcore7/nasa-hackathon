package com.knockoutsong.server.audio;

import com.google.gson.Gson;
import com.knockoutsong.server.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by devnull on 20/10/18.
 */
@Controller
public class AudioController {

    private static final int JUNK_SIZE = 250;

    private static int it = 0;
    private static long SelSongId;

    @Autowired
    private static AudioService service;

    @RequestMapping(value = {"/", "/home"})
    public static String loadPage(){
        return "home";
    }

    @RequestMapping(value = "/error")
    public static String fallTrack(){
        return "redirect:/";
    }

    @RequestMapping(value = "/addAudio/{link}")
    public static String addAudio(@PathVariable String link){
        Audio audio = new Audio();
        audio.setData(AudioProcessing.process(link));
        audio.setPath(link);
        SelSongId = audio.getSONG_ID();
        return "home";
    }

    @RequestMapping(value = "/requestData", method = RequestMethod.POST)
    public static @ResponseBody String getData(){
        it++;
        Audio audio = service.findById(SelSongId);
        List<Double> data = new ArrayList<>();
        for (int i = 0; i < 250; i++) {
            data.add(audio.getData().get(i+it*JUNK_SIZE));
        }
        String JSON = new Gson().toJson(data);
        return JSON;
    }

}
