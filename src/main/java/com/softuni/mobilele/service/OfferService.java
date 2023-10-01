package com.softuni.mobilele.service;

import com.softuni.mobilele.model.ModelEntity;
import com.softuni.mobilele.model.OfferEntity;
import com.softuni.mobilele.model.UserEntity;
import com.softuni.mobilele.model.dto.AddOfferDTO;
import com.softuni.mobilele.model.mapper.OfferMapper;
import com.softuni.mobilele.repository.ModelRepository;
import com.softuni.mobilele.repository.OfferRepository;
import com.softuni.mobilele.repository.UserRepository;
import com.softuni.mobilele.user.CurrentUser;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final UserRepository userRepository;

    private final ModelRepository modelRepository;
    private final CurrentUser currentUser;

    public OfferService(OfferRepository offerRepository, OfferMapper offerMapper, UserRepository userRepository, ModelRepository modelRepository, CurrentUser currentUser) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.currentUser = currentUser;
    }


    public void addOffer(AddOfferDTO addOfferDTO) {
        OfferEntity newOffer = offerMapper.addOfferDtoToOfferEntity(addOfferDTO);

        // TODO - current user should be logged in

        UserEntity userEntity = userRepository.findByEmail(currentUser.getEmail()).orElseThrow();

        ModelEntity model = modelRepository.findById(addOfferDTO.getModelId()).orElseThrow();

        newOffer.setModel(model);
        newOffer.setSeller(userEntity);

        offerRepository.save(newOffer);
    }

}
