package com.softuni.mobilele.service;

import com.softuni.mobilele.model.BrandEntity;
import com.softuni.mobilele.model.ModelEntity;
import com.softuni.mobilele.model.dto.BrandDTO;
import com.softuni.mobilele.model.dto.ModelDTO;
import com.softuni.mobilele.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<BrandDTO> getAllBrands() {
        return brandRepository
                .findAll()
                .stream()
                .map(this::mapBrand)
                .collect(Collectors.toList());

    }

    private BrandDTO mapBrand(BrandEntity brandEntity) {
        List<ModelDTO> models = brandEntity
                .getModels()
                .stream()
                .map(this::mapModel)
                .toList();


        return new BrandDTO()
                .setModels(models)
                .setName(brandEntity.getName());

    }

    private ModelDTO mapModel(ModelEntity model) {
        return new ModelDTO()
                .setId(model.getId())
                .setName(model.getName());

    }
}
