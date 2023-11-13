package com.softuni.mobilele.service;

import com.softuni.mobilele.model.ModelEntity;
import com.softuni.mobilele.model.OfferEntity;
import com.softuni.mobilele.model.UserEntity;
import com.softuni.mobilele.model.dto.AddOfferDTO;
import com.softuni.mobilele.model.dto.OfferDetailDTO;
import com.softuni.mobilele.model.mapper.OfferMapper;
import com.softuni.mobilele.repository.ModelRepository;
import com.softuni.mobilele.repository.OfferRepository;
import com.softuni.mobilele.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;

    public OfferService(OfferRepository offerRepository, OfferMapper offerMapper, UserRepository userRepository, ModelRepository modelRepository) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
    }

    public Page<OfferDetailDTO> getAllOffers(Pageable pageable) {
        return offerRepository
                .findAll(pageable)
                .map(offerMapper::offerEntityToOfferDetailDTO);

    }


    public void addOffer(AddOfferDTO addOfferDTO, UserDetails userDetails) {
        OfferEntity newOffer = offerMapper.offerDtoToOfferEntity(addOfferDTO);

        UserEntity seller = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();

        ModelEntity model = modelRepository.findById(addOfferDTO.getModelId()).orElseThrow();

        newOffer.setModel(model);
        newOffer.setSeller(seller);

        offerRepository.save(newOffer);
    }

}
