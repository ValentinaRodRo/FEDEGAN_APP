package com.backend.service;

import com.backend.model.ImportRecord;
import com.backend.repository.ImportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportService {

    private final ImportRepository importRepository;

    public ImportRecord addImport(ImportRecord importData) {
        return importRepository.save(importData);
    }

    public List<ImportRecord> getAllImports() {
        return importRepository.findAll();
    }
}
