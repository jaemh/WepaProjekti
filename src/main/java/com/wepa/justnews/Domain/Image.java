package com.wepa.justnews.Domain;


import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Entity
public class Image extends AbstractPersistable<Long> {

    @Lob
    @NotEmpty
    @Basic(fetch =FetchType.LAZY)
    private byte[] imageData;
    private String MediaType;
    private long Size;

    @OneToOne
    private News relatedNews;


    public byte[] getImageData() {
        return imageData;
    }

    @Transactional
    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public News getRelatedNews() {
        return relatedNews;
    }

    @Transactional
    public void setRelatedNews(News relatedNews) {
        this.relatedNews = relatedNews;
    }


    public String getMediaType() {
        return MediaType;
    }

    @Transactional
    public void setMediaType(String mediaType) {
        MediaType = mediaType;
    }

    public long getSize() {
        return Size;
    }

    @Transactional
    public void setSize(long size) {
        Size = size;
    }
}
