package com.wepa.justnews.service;


import com.wepa.justnews.Domain.News;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidatorServices {

    private boolean validated;


    private List<String> errors = new ArrayList<>();

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Boolean validateNews(News news) {
        this.errors.clear();
        if (news.getTitle().isEmpty()) {
            this.errors.add("Please, add title.");
        }

        if (news.getRelatedImage() != null && news.getRelatedImage().getSize() > 1000000) {
            this.errors.add("Image is too big.");
        }

        if (news.getRelatedImage() == null) {
            this.errors.add("Please, add image.");
        }

        if (news.getLeadParagraph().isEmpty()) {
            this.errors.add("Please, add paragraph.");
        }

        if (news.getText().isEmpty()) {
            this.errors.add("Please, add text.");
        }


        if (news.getDate() == null) {
            this.errors.add("Please set a publish date.");
        }

        if (news.getRelatedWriter() == null){
            this.errors.add("Please, add writer.");

        }

        if(!this.errors.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

}
