package com.crio.rentalvideo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoRequest {

    private String title;
    private String director;
    private String genre;
}