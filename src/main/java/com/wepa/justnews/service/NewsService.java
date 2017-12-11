package com.wepa.justnews.service;


import com.wepa.justnews.Domain.Category;
import com.wepa.justnews.Domain.Image;
import com.wepa.justnews.Domain.News;
import com.wepa.justnews.Repository.CategoryRepository;
import com.wepa.justnews.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@Service
public class NewsService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Image saveImage(MultipartFile file) throws IOException {
        Image i = new Image();

        i.setMediaType(file.getContentType());
        i.setSize(file.getSize());
        i.setImageData(file.getBytes());

        return imageRepository.save(i);
    }
}
