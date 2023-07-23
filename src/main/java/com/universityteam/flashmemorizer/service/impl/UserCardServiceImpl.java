package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.converter.UserCardConverter;
import com.universityteam.flashmemorizer.dto.UserCardDTO;
import com.universityteam.flashmemorizer.entity.UserCard;
import com.universityteam.flashmemorizer.keys.UserCardId;
import com.universityteam.flashmemorizer.repository.UserCardRepository;
import com.universityteam.flashmemorizer.service.UserCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCardServiceImpl implements UserCardService {

    @Autowired
    private UserCardRepository userCardRepo;

    @Autowired
    private UserCardConverter userCardConverter;

    @Override
    public UserCardDTO add(UserCardDTO userCardDTO) {
        UserCard userCard = userCardConverter.convertDtoToEntity(userCardDTO);
        try {
            UserCard added = userCardRepo.save(userCard);
            return userCardConverter.convertEntityToDto(added);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserCardDTO update(UserCardDTO userCardDTO) {
        Optional<UserCard> optUser = userCardRepo.findById(
                new UserCardId(
                        userCardDTO.getCard().getId(),
                        userCardDTO.getUser().getId()
                ));
        if (optUser.isEmpty()) return null;
        try {
            UserCard updated = userCardRepo.save(optUser.get());
            return userCardConverter.convertEntityToDto(updated);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserCardDTO getById(UserCardId Id) {
        UserCard userCard = userCardRepo.findById(Id).get();
        return userCardConverter.convertEntityToDto(userCard);
    }
}
