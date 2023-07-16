package com.universityteam.flashmemorizer.service.impl;

import com.universityteam.flashmemorizer.converter.SharedDeckConverter;
import com.universityteam.flashmemorizer.dto.SharedDeckDTO;
import com.universityteam.flashmemorizer.entity.SharedDeck;
import com.universityteam.flashmemorizer.repository.SharedDeckRepository;
import com.universityteam.flashmemorizer.service.SharedDeckService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class SharedDeckServiceImpl implements SharedDeckService {

    @Autowired
    private SharedDeckRepository shareRepo;

    @Autowired
    private SharedDeckConverter shareConverter;

    @Override
    public SharedDeckDTO add(SharedDeckDTO sharedDeckDTO) {
        SharedDeck sharedDeck = shareConverter.convertDtoToEntity(sharedDeckDTO);
        try {
            SharedDeck added = shareRepo.save(sharedDeck);
            return shareConverter.convertEntityToDto(added);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Long id) {
        Optional<SharedDeck> optShared = shareRepo.findById(id);
        if (optShared.isEmpty()) return false;
        try {
            shareRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public SharedDeckDTO update(SharedDeckDTO sharedDeckDTO) {
        Optional<SharedDeck> optShared = shareRepo.findById(sharedDeckDTO.getId());
        if (optShared.isEmpty()) return null;
        try {
            SharedDeck updated = shareRepo.save(optShared.get());
            return shareConverter.convertEntityToDto(updated);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SharedDeckDTO> getBySenderId(Long senderId) {
        List<SharedDeck> sharedDecks = shareRepo.findBySenderId(senderId);
        return shareConverter.convertEntityToDto(sharedDecks);
    }
}
