package com.backend.controller;

import com.backend.model.ImportRecord;
import com.backend.model.InfectionRecord;
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
    public ResponseEntity<ImportRecord> addImportData(@RequestBody ImportRecord importData) {
        ImportRecord savedImport = importService.addImport(importData);
        return new ResponseEntity<>(savedImport, HttpStatus.CREATED);
    }

    @GetMapping("/imports")
    public ResponseEntity<List<ImportRecord>> getAllImportData() {
        List<ImportRecord> imports = importService.getAllImports();
        return new ResponseEntity<>(imports, HttpStatus.OK);
    }

    @PostMapping("/infections")
    public ResponseEntity<InfectionRecord> addInfectionData(@RequestBody InfectionRecord infection) {
        InfectionRecord savedInfection = infectionService.addInfection(infection);
        return new ResponseEntity<>(savedInfection, HttpStatus.CREATED);
    }

    @GetMapping("/infections")
    public ResponseEntity<List<InfectionRecord>> getAllInfectionData() {
        List<InfectionRecord> infections = infectionService.getAllInfections();
        return new ResponseEntity<>(infections, HttpStatus.OK);
    }
}
