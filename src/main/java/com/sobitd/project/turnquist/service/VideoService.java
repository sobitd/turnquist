package com.sobitd.project.turnquist.service;

import com.sobitd.project.turnquist.model.Video;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class VideoService {
    private List<Video> videos = List.of(
        new Video("Need Help with your SPRING BOOT App?"),
        new Video("Don't do THIS to your own CODE!"),
        new Video("SECRETS to fix BROKEN CODE!")
    );

    public List<Video> getVideos() {
        return videos;
    }

    public Video create(Video newVideo) {
        List<Video> extend = new ArrayList<>(videos);
        extend.add(newVideo);
        this.videos = List.copyOf(extend);
        return newVideo;
    }
}
