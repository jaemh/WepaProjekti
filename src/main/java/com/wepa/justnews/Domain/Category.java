package com.wepa.justnews.Domain;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Category extends AbstractPersistable<Long> {

    private String category;


    public String getCategory() {
        return category;
    }

    @Transactional
    public void setCategory(String category) {
        this.category = category;
    }
}
