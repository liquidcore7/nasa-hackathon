package com.knockoutsong.server.audio;

import javax.persistence.*;
import java.util.List;

/**
 * Created by devnull on 21/10/18.
 */
@Entity
public class Audio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long SONG_ID;
    @Column(columnDefinition = "NCHAR(128)")
    private String name;
    @Column(nullable = false, columnDefinition = "NCHAR(128)")
    private String path;
    @Transient
    private List<Double> data;

    public long getSONG_ID() {
        return SONG_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }

    public Audio() {

    }

    public Audio(String name, String path) {
        this.name = name;
        this.path = path;
    }

    @Override
    public String toString() {
        return "Audio{" +
                "SONG_ID=" + SONG_ID +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", data=" + data +
                '}';
    }
}
