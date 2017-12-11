package com.wepa.justnews.Domain;

import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.transaction.annotation.Transactional;

@Entity
    public class Writer extends AbstractPersistable<Long> {

        private String name;

        @Transactional
        public String getName() {
            return name;
        }

        @Transactional
        public void setName(String name) {
            this.name = name;
        }

    }

