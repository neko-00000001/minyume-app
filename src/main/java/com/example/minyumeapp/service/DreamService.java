package com.example.minyumeapp.service;

import com.example.minyumeapp.model.Dream;
import com.example.minyumeapp.repository.DreamRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DreamService {

    private final DreamRepository dreamRepository;

    public DreamService(DreamRepository dreamRepository) {
        this.dreamRepository = dreamRepository;
    }

    public void saveDream(Dream dream) {
        dream.setPostedAt(LocalDateTime.now());
        dreamRepository.save(dream);
    }

    public List<Dream> getDreamsFromLast24Hours() {
        return dreamRepository.findByPostedAtAfter(LocalDateTime.now().minusHours(24));
    }
}
