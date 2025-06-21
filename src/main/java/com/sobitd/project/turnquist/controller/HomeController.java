package com.sobitd.project.turnquist.controller;

import com.sobitd.project.turnquist.model.Video;
import com.sobitd.project.turnquist.model.VideoSearch;
import com.sobitd.project.turnquist.service.VideoService;
import com.sobitd.project.turnquist.entity.VideoEntity;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class HomeController {
    private final VideoService videoService;

    public HomeController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("videos", videoService.getVideos());
        return "index";
    }

    @PostMapping("/new-video")
    public String newVideo(@ModelAttribute Video newVideo) {
        videoService.create(newVideo);
        return "redirect:/";
    }

    @PostMapping("/multi-field-search")
    public String multiFieldSearch( 
        @ModelAttribute VideoSearch search, 
        Model model) {
            List<VideoEntity> searchResults = 
                videoService.search(search);
        model.addAttribute("videos", searchResults);
        return "index";
    }

    @GetMapping("/react")
    public String react() {
        return "react";
    }
}
