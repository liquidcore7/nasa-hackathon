package com.knockoutsong.server.service;

import com.knockoutsong.server.audio.Audio;

import java.util.List;

/**
 * Created by devnull on 21/10/18.
 */
public interface AudioService {

    void save(Audio audio);
    List<Audio> findAll();
    Audio findById(long ID);


}
