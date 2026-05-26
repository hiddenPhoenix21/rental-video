package com.crio.rentalvideo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crio.rentalvideo.entity.Rental;
import com.crio.rentalvideo.entity.User;
import com.crio.rentalvideo.entity.Video;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findByUserAndActiveTrue(User user);

    Rental findByUserAndVideoAndActiveTrue(User user, Video video);
}