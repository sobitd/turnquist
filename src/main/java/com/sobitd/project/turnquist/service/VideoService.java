package com.sobitd.project.turnquist.service;

import com.sobitd.project.turnquist.entity.VideoEntity;
import com.sobitd.project.turnquist.model.Video;
import com.sobitd.project.turnquist.model.VideoSearch;
import com.sobitd.project.turnquist.repository.VideoRepository;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class VideoService {
    private final VideoRepository repository;
    private List<Video> videos;

    public VideoService(VideoRepository repository) {
        this.repository = repository;
        this.videos = new ArrayList<>(List.of(
            new Video("Need Help with your SPRING BOOT App?"),
            new Video("Don't do THIS to your own CODE!"),
            new Video("SECRETS to fix BROKEN CODE!")
        ));
    }

    public List<Video> getVideos() {
        return Collections.unmodifiableList(videos);
    }

    public Video create(Video newVideo) {
        videos.add(newVideo);
        return newVideo;
    }

    // do some checks before search
    public List<VideoEntity> search(VideoSearch videoSearch) {
        if (StringUtils.hasText(videoSearch.name()) && StringUtils
        .hasText(videoSearch.description())) {
            return repository.findByNameContainsOrDescriptionContainsAllIgnoreCase(
                videoSearch.name(), videoSearch.description());
        }
        
        if (StringUtils.hasText(videoSearch.name())) {
            return repository.findByNameContainsIgnoreCase
            (videoSearch.name());
        }
    
        if (StringUtils.hasText(videoSearch.description())) {
            return repository.findByDescriptionContainsIgnoreCase
            (videoSearch.description());
        }
        return Collections.emptyList();
    }
}
