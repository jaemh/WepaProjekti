package com.wepa.justnews.Repository;

import com.wepa.justnews.Domain.News;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NewsRepository extends JpaRepository<News, Long> {
    News findByTitle(String title);
}

