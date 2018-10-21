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
    @Column(columnDefinition = "NCHAR(128)")
    private String length;
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

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }

    public Audio() {

    }

    @Override
    public String toString() {
        return "Audio{" +
                "SONG_ID=" + SONG_ID +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", length='" + length + '\'' +
                ", data=" + data +
                '}';
    }
}
