package com.example.minyumeapp.repository;

import com.example.minyumeapp.model.Dream;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DreamRepository extends JpaRepository<Dream, Long> {
    List<Dream> findByPostedAtAfter(LocalDateTime time);
}
