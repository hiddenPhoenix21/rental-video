package com.crio.rentalvideo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crio.rentalvideo.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

}