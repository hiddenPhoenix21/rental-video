package com.crio.rentalvideo.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.crio.rentalvideo.entity.*;
import com.crio.rentalvideo.repository.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class RentalController {

    private final RentalRepository rentalRepository;

    private final UserRepository userRepository;

    private final VideoRepository videoRepository;

    @PostMapping("/videos/{videoId}/rent")
    public String rentVideo(
            @PathVariable Long videoId,
            Authentication authentication) {

        User user = userRepository
                .findByEmail(authentication.getName())
                .orElseThrow();

        List<Rental> activeRentals =
                rentalRepository.findByUserAndActiveTrue(user);

        if (activeRentals.size() >= 2) {
            throw new RuntimeException(
                    "Maximum 2 rentals allowed"
            );
        }

        Video video = videoRepository
                .findById(videoId)
                .orElseThrow();

        Rental rental = new Rental();

        rental.setUser(user);

        rental.setVideo(video);

        rentalRepository.save(rental);

        return "Video rented";
    }

    @PostMapping("/videos/{videoId}/return")
    public String returnVideo(
            @PathVariable Long videoId,
            Authentication authentication) {

        User user = userRepository
                .findByEmail(authentication.getName())
                .orElseThrow();

        Video video = videoRepository
                .findById(videoId)
                .orElseThrow();

        Rental rental =
                rentalRepository
                        .findByUserAndVideoAndActiveTrue(
                                user,
                                video
                        );

        rental.setActive(false);

        rentalRepository.save(rental);

        return "Video returned";
    }
}