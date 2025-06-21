package com.sobitd.project.turnquist.repository;

import com.sobitd.project.turnquist.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository <VideoEntity, Long> {
    List<VideoEntity> findByName (String name);
    List<VideoEntity> findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String name, String description);
    List<VideoEntity> findByNameContainsIgnoreCase(String name);
    List<VideoEntity> findByDescriptionContainsIgnoreCase(String description);

}