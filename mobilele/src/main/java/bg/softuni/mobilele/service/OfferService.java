package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.AddOfferDTO;
import bg.softuni.mobilele.model.entity.ModelEntity;
import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.mapper.OfferMapper;
import bg.softuni.mobilele.repository.ModelRepository;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.user.CurrentUser;
import org.springframework.stereotype.Service;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final OfferMapper offerMapper;
    private final ModelRepository modelRepository;

    public OfferService(OfferRepository offerRepository, UserRepository userRepository, CurrentUser currentUser, OfferMapper offerMapper, ModelRepository modelRepository) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.offerMapper = offerMapper;
        this.modelRepository = modelRepository;
    }

    public void addOffer(AddOfferDTO addOfferDTO) {
        OfferEntity newOffer = offerMapper.mapAddOfferDtoToOfferEntity(addOfferDTO);

        UserEntity seller = userRepository.findByEmail(currentUser.getEmail()).orElseThrow();

        ModelEntity model = modelRepository.getReferenceById(addOfferDTO.getModelId());

        newOffer
                .setModel(model)
                .setSeller(seller);

        this.offerRepository.save(newOffer);

    }

}
