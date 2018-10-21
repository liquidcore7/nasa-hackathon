package com.knockoutsong.server.audio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by devnull on 20/10/18.
 */
@Controller
public class AudioController {

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
        AudioProcessing.process(link);
        audio.setPath(link);
//        audio.setLength();
        return "home";
    }

//    @RequestMapping(value = "/requestData", method = RequestMethod.GET)
//    public static @ResponseBody String getData(){
//
//        String JSON = new Gson().toJson(audio.getData());
//        return JSON;
//    }

}
