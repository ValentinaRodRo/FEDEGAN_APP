package com.backend.repository;


import com.backend.model.InfectionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfectionRepository extends JpaRepository<InfectionRecord, Long> {
}