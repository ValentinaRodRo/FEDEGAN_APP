package com.backend.service;

import com.backend.dto.InfectionRequestDTO;
import com.backend.dto.InfectionResponseDTO;
import com.backend.model.ColombianCity;
import com.backend.model.InfectionRecord;
import com.backend.repository.InfectionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class InfectionServiceTest {

    @Mock
    private InfectionRepository infectionRepository;

    @InjectMocks
    private InfectionService infectionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addInfection_shouldSaveAndReturnResponseDTO() {
        // Arrange
        InfectionRequestDTO request = new InfectionRequestDTO();
        request.setCity(ColombianCity.CALI);
        request.setRadius(12.5);
        request.setDisease("Zika");
        request.setCases(42);
        request.setRegion("Valle del Cauca");

        InfectionRecord savedRecord = new InfectionRecord();
        savedRecord.setId(99L);
        savedRecord.setCity(ColombianCity.CALI);
        savedRecord.setLat(3.4517);
        savedRecord.setLng(-76.5320);
        savedRecord.setRadius(12.5);
        savedRecord.setDisease("Zika");
        savedRecord.setCases(42);
        savedRecord.setRegion("Valle del Cauca");

        when(infectionRepository.save(any(InfectionRecord.class))).thenReturn(savedRecord);

        // Act
        InfectionResponseDTO response = infectionService.addInfection(request);

        // Assert
        ArgumentCaptor<InfectionRecord> captor = ArgumentCaptor.forClass(InfectionRecord.class);
        verify(infectionRepository, times(1)).save(captor.capture());
        InfectionRecord toSave = captor.getValue();
        assertThat(toSave.getCity()).isEqualTo(ColombianCity.CALI);
        assertThat(toSave.getRadius()).isEqualTo(12.5);
        assertThat(toSave.getDisease()).isEqualTo("Zika");
        assertThat(toSave.getCases()).isEqualTo(42);
        assertThat(toSave.getRegion()).isEqualTo("Valle del Cauca");

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(99L);
        assertThat(response.getCity()).isEqualTo("CALI");
        assertThat(response.getLat()).isEqualTo(3.4517);
        assertThat(response.getLng()).isEqualTo(-76.5320);
        assertThat(response.getRadius()).isEqualTo(12.5);
        assertThat(response.getDisease()).isEqualTo("Zika");
        assertThat(response.getCases()).isEqualTo(42);
        assertThat(response.getRegion()).isEqualTo("Valle del Cauca");
    }

    @Test
    void getAllInfections_shouldReturnListOfResponseDTOs() {
        // Arrange
        InfectionRecord rec1 = new InfectionRecord();
        rec1.setId(1L);
        rec1.setCity(ColombianCity.BOGOTA);
        rec1.setLat(4.7110);
        rec1.setLng(-74.0721);
        rec1.setRadius(5.0);
        rec1.setDisease("Dengue");
        rec1.setCases(100);
        rec1.setRegion("Cundinamarca");

        InfectionRecord rec2 = new InfectionRecord();
        rec2.setId(2L);
        rec2.setCity(ColombianCity.MEDELLIN);
        rec2.setLat(6.2442);
        rec2.setLng(-75.5812);
        rec2.setRadius(8.0);
        rec2.setDisease("Chikungunya");
        rec2.setCases(50);
        rec2.setRegion("Antioquia");

        when(infectionRepository.findAll()).thenReturn(Arrays.asList(rec1, rec2));

        // Act
        List<InfectionResponseDTO> results = infectionService.getAllInfections();

        // Assert
        verify(infectionRepository, times(1)).findAll();
        assertThat(results).hasSize(2);

        InfectionResponseDTO dto1 = results.get(0);
        assertThat(dto1.getId()).isEqualTo(1L);
        assertThat(dto1.getCity()).isEqualTo("BOGOTA");
        assertThat(dto1.getLat()).isEqualTo(4.7110);
        assertThat(dto1.getLng()).isEqualTo(-74.0721);
        assertThat(dto1.getRadius()).isEqualTo(5.0);
        assertThat(dto1.getDisease()).isEqualTo("Dengue");
        assertThat(dto1.getCases()).isEqualTo(100);
        assertThat(dto1.getRegion()).isEqualTo("Cundinamarca");

        InfectionResponseDTO dto2 = results.get(1);
        assertThat(dto2.getId()).isEqualTo(2L);
        assertThat(dto2.getCity()).isEqualTo("MEDELLIN");
        assertThat(dto2.getLat()).isEqualTo(6.2442);
        assertThat(dto2.getLng()).isEqualTo(-75.5812);
        assertThat(dto2.getRadius()).isEqualTo(8.0);
        assertThat(dto2.getDisease()).isEqualTo("Chikungunya");
        assertThat(dto2.getCases()).isEqualTo(50);
        assertThat(dto2.getRegion()).isEqualTo("Antioquia");
    }
}
