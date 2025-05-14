package com.backend.controller;

import com.backend.dto.ImportRequestDTO;
import com.backend.dto.ImportResponseDTO;
import com.backend.dto.InfectionRequestDTO;
import com.backend.dto.InfectionResponseDTO;
import com.backend.service.ImportService;
import com.backend.service.InfectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DataController {

    private final ImportService importService;
    private final InfectionService infectionService;

    @PostMapping("/imports")
    public ResponseEntity<ImportResponseDTO> addImportData(@RequestBody ImportRequestDTO importRequestDTO) {
        ImportResponseDTO savedImport = importService.addImport(importRequestDTO);
        return new ResponseEntity<>(savedImport, HttpStatus.CREATED);
    }

    @GetMapping("/imports")
    public ResponseEntity<List<ImportResponseDTO>> getAllImportData() {
        List<ImportResponseDTO> imports = importService.getAllImports();
        return new ResponseEntity<>(imports, HttpStatus.OK);
    }

    @PostMapping("/infections")
    public ResponseEntity<InfectionResponseDTO> addInfectionData(@RequestBody InfectionRequestDTO infectionRequestDTO) {
        InfectionResponseDTO savedInfection = infectionService.addInfection(infectionRequestDTO);
        return new ResponseEntity<>(savedInfection, HttpStatus.CREATED);
    }

    @GetMapping("/infections")
    public ResponseEntity<List<InfectionResponseDTO>> getAllInfectionData() {
        List<InfectionResponseDTO> infections = infectionService.getAllInfections();
        return new ResponseEntity<>(infections, HttpStatus.OK);
    }
}
