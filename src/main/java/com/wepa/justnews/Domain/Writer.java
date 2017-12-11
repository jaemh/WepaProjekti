package com.wepa.justnews.Domain;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

    @Entity
    public class Writer extends AbstractPersistable<Long> {

    @ManyToMany(mappedBy = "relatedWriter")
    private List<News> relatedNews;

    private String name;


    public String getName() {
        return name;
    }

    @Transactional
    public void setName(String name) {
    this.name = name;
    }

    public List<News> getRelatedNews() {
        return relatedNews;
    }

    @Transactional
    public void setRelatedNews(List<News> relatedNews) {
        this.relatedNews = relatedNews;
    }

}






