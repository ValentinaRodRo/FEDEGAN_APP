package com.backend.service;

import com.backend.dto.InfectionRequestDTO;
import com.backend.dto.InfectionResponseDTO;
import com.backend.model.InfectionRecord;
import com.backend.repository.InfectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InfectionService {

    private final InfectionRepository infectionRepository;

    public InfectionResponseDTO addInfection(InfectionRequestDTO infectionRequestDTO) {
        InfectionRecord infection = new InfectionRecord();
        infection.setLat(infectionRequestDTO.getLat());
        infection.setLng(infectionRequestDTO.getLng());
        infection.setRadius(infectionRequestDTO.getRadius());
        infection.setDisease(infectionRequestDTO.getDisease());
        infection.setCases(infectionRequestDTO.getCases());
        infection.setRegion(infectionRequestDTO.getRegion());

        InfectionRecord savedInfection = infectionRepository.save(infection);
        return convertToResponseDTO(savedInfection);
    }

    public List<InfectionResponseDTO> getAllInfections() {
        return infectionRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private InfectionResponseDTO convertToResponseDTO(InfectionRecord infection) {
        InfectionResponseDTO responseDTO = new InfectionResponseDTO();
        responseDTO.setId(infection.getId());
        responseDTO.setLat(infection.getLat());
        responseDTO.setLng(infection.getLng());
        responseDTO.setRadius(infection.getRadius());
        responseDTO.setDisease(infection.getDisease());
        responseDTO.setCases(infection.getCases());
        responseDTO.setRegion(infection.getRegion());
        return responseDTO;
    }
}