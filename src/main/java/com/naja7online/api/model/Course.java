package com.naja7online.api.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 1000)
    private String description;
    private String imageUrl;
    private String level; // e.g., "2eme_BAC"
    private String subject; // e.g., "Mathematiques"

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Module> modules;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getLevel() {
        return level;
    }

    public String getSubject() {
        return subject;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}