package com.backend.service;

import com.backend.model.InfectionRecord;
import com.backend.repository.InfectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InfectionService {

    private final InfectionRepository infectionRepository;

    public InfectionRecord addInfection(InfectionRecord infection) {
        return infectionRepository.save(infection);
    }

    public List<InfectionRecord> getAllInfections() {
        return infectionRepository.findAll();
    }
}
