package com.javaguide.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javaguide.springboot.entity.wikimediaData;

public interface WikimediaDataRepository extends JpaRepository<wikimediaData, Long> {
}
