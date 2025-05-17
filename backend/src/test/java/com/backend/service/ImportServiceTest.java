package com.backend.service;

import com.backend.dto.ImportRequestDTO;
import com.backend.dto.ImportResponseDTO;
import com.backend.model.ColombianCity;
import com.backend.model.ImportRecord;
import com.backend.repository.ImportRepository;
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

class ImportServiceTest {

    @Mock
    private ImportRepository importRepository;

    @InjectMocks
    private ImportService importService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addImport_shouldSaveAndReturnResponseDTO() {
        // Arrange
        ImportRequestDTO request = new ImportRequestDTO();
        request.setAmount(5);
        request.setCityExit(ColombianCity.BOGOTA);
        request.setCityEntry(ColombianCity.CALI);

        ImportRecord savedRecord = new ImportRecord();
        savedRecord.setId(123L);
        savedRecord.setAmount(5);
        savedRecord.setCityExit(ColombianCity.BOGOTA);
        savedRecord.setCityEntry(ColombianCity.CALI);
        savedRecord.setLat(4.7110);
        savedRecord.setLng(-74.0721);

        when(importRepository.save(any(ImportRecord.class))).thenReturn(savedRecord);

        // Act
        ImportResponseDTO response = importService.addImport(request);

        // Assert
        ArgumentCaptor<ImportRecord> captor = ArgumentCaptor.forClass(ImportRecord.class);
        verify(importRepository, times(1)).save(captor.capture());
        ImportRecord toSave = captor.getValue();
        assertThat(toSave.getAmount()).isEqualTo(5);
        assertThat(toSave.getCityExit()).isEqualTo(ColombianCity.BOGOTA);
        assertThat(toSave.getCityEntry()).isEqualTo(ColombianCity.CALI);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(123L);
        assertThat(response.getAmount()).isEqualTo(5);
        assertThat(response.getCityExit()).isEqualTo("BOGOTA");
        assertThat(response.getCityEntry()).isEqualTo("CALI");
        assertThat(response.getLat()).isEqualTo(4.7110);
        assertThat(response.getLng()).isEqualTo(-74.0721);
    }

    @Test
    void getAllImports_shouldReturnListOfResponseDTOs() {
        // Arrange
        ImportRecord rec1 = new ImportRecord();
        rec1.setId(1L);
        rec1.setAmount(10);
        rec1.setCityExit(ColombianCity.MEDELLIN);
        rec1.setCityEntry(ColombianCity.CARTAGENA);
        rec1.setLat(6.2442);
        rec1.setLng(-75.5812);

        ImportRecord rec2 = new ImportRecord();
        rec2.setId(2L);
        rec2.setAmount(20);
        rec2.setCityExit(ColombianCity.CALI);
        rec2.setCityEntry(ColombianCity.BARRANQUILLA);
        rec2.setLat(3.4517);
        rec2.setLng(-76.5320);

        when(importRepository.findAll()).thenReturn(Arrays.asList(rec1, rec2));

        // Act
        List<ImportResponseDTO> results = importService.getAllImports();

        // Assert
        verify(importRepository, times(1)).findAll();
        assertThat(results).hasSize(2);

        ImportResponseDTO dto1 = results.get(0);
        assertThat(dto1.getId()).isEqualTo(1L);
        assertThat(dto1.getAmount()).isEqualTo(10);
        assertThat(dto1.getCityExit()).isEqualTo("MEDELLIN");
        assertThat(dto1.getCityEntry()).isEqualTo("CARTAGENA");
        assertThat(dto1.getLat()).isEqualTo(6.2442);
        assertThat(dto1.getLng()).isEqualTo(-75.5812);

        ImportResponseDTO dto2 = results.get(1);
        assertThat(dto2.getId()).isEqualTo(2L);
        assertThat(dto2.getAmount()).isEqualTo(20);
        assertThat(dto2.getCityExit()).isEqualTo("CALI");
        assertThat(dto2.getCityEntry()).isEqualTo("BARRANQUILLA");
        assertThat(dto2.getLat()).isEqualTo(3.4517);
        assertThat(dto2.getLng()).isEqualTo(-76.5320);
    }
}
