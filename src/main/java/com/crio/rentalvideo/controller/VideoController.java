package com.crio.rentalvideo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crio.rentalvideo.dto.VideoRequest;
import com.crio.rentalvideo.entity.Video;
import com.crio.rentalvideo.service.VideoService;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping
    public ResponseEntity<Video> createVideo(
            @RequestBody VideoRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(videoService.createVideo(request));
    }

    @GetMapping
    public ResponseEntity<List<Video>> getAllVideos() {

        return ResponseEntity.ok(
                videoService.getAllVideos()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Video> updateVideo(
            @PathVariable Long id,
            @RequestBody VideoRequest request) {

        return ResponseEntity.ok(
                videoService.updateVideo(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVideo(
            @PathVariable Long id) {

        videoService.deleteVideo(id);

        return ResponseEntity.ok("Video deleted");
    }
}