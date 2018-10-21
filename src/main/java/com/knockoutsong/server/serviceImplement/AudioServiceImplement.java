package com.knockoutsong.server.serviceImplement;


import com.knockoutsong.server.audio.Audio;
import com.knockoutsong.server.repository.AudioRepository;
import com.knockoutsong.server.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by devnull on 21/10/18.
 */
@Service
public class AudioServiceImplement implements AudioService {

    @Autowired
    private AudioRepository repository;

    @Override
    public void save(Audio audio) {
        repository.save(audio);
    }

    @Override
    public Audio findById(long ID) {
        return findById(ID);
    }

    @Override
    public List<Audio> findAll() {
        return repository.findAll();
    }
}
