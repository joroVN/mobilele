package com.softuni.mobilele.entities;

import com.softuni.mobilele.entities.enums.CategoryEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;
    @Column(nullable = false)
    private String imageUrl;

    private int startYear;
    private int endYear;
    @ManyToOne
    private BrandEntity brand;

    private LocalDateTime created;
    private LocalDateTime modified;


    public ModelEntity() {
    }

    public String getName() {
        return name;
    }

    public ModelEntity setName(String name) {
        this.name = name;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public ModelEntity setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ModelEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public int getStartYear() {
        return startYear;
    }

    public ModelEntity setStartYear(int startYear) {
        this.startYear = startYear;
        return this;
    }

    public int getEndYear() {
        return endYear;
    }

    public ModelEntity setEndYear(int endYear) {
        this.endYear = endYear;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public ModelEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public ModelEntity setModified(LocalDateTime modified) {
        this.modified = modified;
        return this;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public ModelEntity setBrand(BrandEntity brand) {
        this.brand = brand;
        return this;
    }
}
