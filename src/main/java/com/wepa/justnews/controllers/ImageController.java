package com.wepa.justnews.controllers;


import com.wepa.justnews.Domain.Image;
import com.wepa.justnews.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;


    @GetMapping(path = "/image/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> serveImage(@PathVariable Long id) {
        Image imageFromDB = imageRepository.findOne(id);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(imageFromDB.getMediaType()));
        headers.setContentLength(imageFromDB.getSize());

        return new ResponseEntity<>(imageFromDB.getImageData(), headers, HttpStatus.OK);
    }

}
