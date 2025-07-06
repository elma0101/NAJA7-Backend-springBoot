package com.naja7online.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String videoUrl;
    @Column(length = 5000)
    private String content; // For text-based lessons or descriptions

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    private Module module;

    // Getters and Setters...

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getContent() {
        return content;
    }

    public Module getModule() {
        return module;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}