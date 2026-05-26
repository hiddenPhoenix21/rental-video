package com.crio.rentalvideo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.rentalvideo.dto.VideoRequest;
import com.crio.rentalvideo.entity.Video;
import com.crio.rentalvideo.repository.VideoRepository;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public Video createVideo(VideoRequest request) {

        Video video = new Video();

        video.setTitle(request.getTitle());
        video.setDirector(request.getDirector());
        video.setGenre(request.getGenre());
        video.setAvailable(true);

        return videoRepository.save(video);
    }

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public Video updateVideo(Long id, VideoRequest request) {

        Video video = videoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Video not found"));

        video.setTitle(request.getTitle());
        video.setDirector(request.getDirector());
        video.setGenre(request.getGenre());

        return videoRepository.save(video);
    }

    public void deleteVideo(Long id) {

        Video video = videoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Video not found"));

        videoRepository.delete(video);
    }
}