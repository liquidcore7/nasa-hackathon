package com.knockoutsong.server.repository;

import com.knockoutsong.server.audio.Audio;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by devnull on 21/10/18.
 */
public interface AudioRepository extends JpaRepository<Audio, Long> {
}
