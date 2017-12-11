package com.wepa.justnews.Repository;

import com.wepa.justnews.Domain.Writer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface WriterRepository extends JpaRepository<Writer, Long>{
    Writer findByName(String writer);
}
