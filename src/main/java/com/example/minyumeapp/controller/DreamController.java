package com.example.minyumeapp.controller;

import com.example.minyumeapp.model.Dream;
import com.example.minyumeapp.model.Emotion;
import com.example.minyumeapp.service.DreamService;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

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
        int count = dreamService.getAllDreams().size(); // 投稿数をすべてカウント
        model.addAttribute("count", count);
        return "result"; // templates/result.html を表示
    }

    @GetMapping("/dreams")
    public String listDreams(Model model) {
        List<Dream> dreams = dreamService.getAllDreams();

        Map<Emotion, List<Dream>> dreamsByEmotion = new LinkedHashMap<>();
        // 各感情ごとに空のリストを初期化
        for (Emotion emotion : Emotion.values()) {
            dreamsByEmotion.put(emotion, new ArrayList<>());
        }
        // 各夢を感情ごとにリストに追加
        for (Dream dream : dreams) {
            dreamsByEmotion.get(dream.getEmotion()).add(dream);
        }

        model.addAttribute("dreamsByEmotion", dreamsByEmotion);
        return "dreams"; // → templates/dreams.html を表示
    }

}