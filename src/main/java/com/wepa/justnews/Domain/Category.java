package com.wepa.justnews.Domain;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Category extends AbstractPersistable<Long> {


    @ManyToMany(mappedBy = "relatedCategory")
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

    public void setRelatedNews(List<News> relatedNews) {
        this.relatedNews = relatedNews;
    }


}



