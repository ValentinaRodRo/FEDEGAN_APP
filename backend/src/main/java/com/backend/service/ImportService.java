package com.backend.service;

import com.backend.dto.ImportRequestDTO;
import com.backend.dto.ImportResponseDTO;
import com.backend.model.ImportRecord;
import com.backend.repository.ImportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImportService {

    private final ImportRepository importRepository;

    public ImportResponseDTO addImport(ImportRequestDTO importRequestDTO) {
        ImportRecord importData = new ImportRecord();
        importData.setAmount(importRequestDTO.getAmount());
        importData.setCityExit(importRequestDTO.getCityExit());
        importData.setCityEntry(importRequestDTO.getCityEntry());

        ImportRecord savedImport = importRepository.save(importData);
        return convertToResponseDTO(savedImport);
    }

    public List<ImportResponseDTO> getAllImports() {
        return importRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private ImportResponseDTO convertToResponseDTO(ImportRecord importData) {
        ImportResponseDTO responseDTO = new ImportResponseDTO();
        responseDTO.setId(importData.getId());
        responseDTO.setAmount(importData.getAmount());
        responseDTO.setCityExit(importData.getCityExit().name());
        responseDTO.setCityEntry(importData.getCityEntry().name());
        responseDTO.setLat(importData.getLat());
        responseDTO.setLng(importData.getLng());
        return responseDTO;
    }
}
