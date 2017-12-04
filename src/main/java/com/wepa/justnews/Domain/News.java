package com.wepa.justnews.Domain;


import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class News extends AbstractPersistable<Long> {

    private String title;
    private String leadParagraph;
    private String text;
    private String image;
    private Date date;

    @Transactional
    public String getTitle() {
        return title;
    }

    @Transactional
    public void setTitle(String title) {
        this.title = title;
    }

    @Transactional
    public String getLeadParagraph() {
        return leadParagraph;
    }

    @Transactional
    public void setLeadParagraph(String leadParagraph) {
        this.leadParagraph = leadParagraph;
    }

    @Transactional
    public String getText() {
        return text;
    }

    @Transactional
    public void setText(String text) {
        this.text = text;
    }

    @Transactional
    public String getImage() {
        return image;
    }

    @Transactional
    public void setImage(String image) {
        this.image = image;
    }

    @Transactional
    public Date getDate() {
        return date;
    }

    @Transactional
    public void setDate(Date date) {
        this.date = date;
    }

}
