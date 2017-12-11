package com.wepa.justnews.Repository;

import com.wepa.justnews.Domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategory(String category);
}
