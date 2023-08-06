package com.universityteam.flashmemorizer.converter;

import com.universityteam.flashmemorizer.dto.VerificationTokenDTO;
import com.universityteam.flashmemorizer.dto.VerificationTokenDTO;
import com.universityteam.flashmemorizer.entity.VerificationToken;
import com.universityteam.flashmemorizer.entity.VerificationToken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VerificationTokenConverter {
    @Autowired
    private ModelMapper mapper;

    public List<VerificationTokenDTO> convertEntityToDto(List<VerificationToken> tokens) {
        return tokens.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public VerificationTokenDTO convertEntityToDto(VerificationToken token) {
        return (token == null) ? null : mapper.map(token, VerificationTokenDTO.class);
    }

    public List<VerificationToken> convertDtoToEntity(List<VerificationTokenDTO> tokenDTOs) {
        return tokenDTOs.stream()
                .map(this::convertDtoToEntity)
                .collect(Collectors.toList());
    }

    public VerificationToken convertDtoToEntity(VerificationTokenDTO tokenDTO) {
        return (tokenDTO == null) ? null : mapper.map(tokenDTO, VerificationToken.class);
    }
}
