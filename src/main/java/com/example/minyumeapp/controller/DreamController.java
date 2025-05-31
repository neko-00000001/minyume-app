package com.example.minyumeapp.controller;

import com.example.minyumeapp.model.Dream;
import com.example.minyumeapp.service.DreamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DreamController {

    private final DreamService dreamService;

    public DreamController(DreamService dreamService) {
        this.dreamService = dreamService;
    }

    // トップページ表示（投稿フォーム）
    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("dream", new Dream());
        return "index"; // templates/index.html を表示
    }

    // 投稿処理
    @PostMapping("/submit")
    public String submitDream(@ModelAttribute Dream dream, Model model) {
        dreamService.saveDream(dream); // 投稿内容を保存
        int count = dreamService.getDreamsFromLast24Hours().size(); // 24時間以内の投稿数
        model.addAttribute("count", count);
        return "result"; // templates/result.html を表示
    }
}