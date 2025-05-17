package com.backend.integration;

import com.backend.dto.ImportRequestDTO;
import com.backend.dto.ImportResponseDTO;
import com.backend.dto.InfectionRequestDTO;
import com.backend.dto.InfectionResponseDTO;
import com.backend.model.ColombianCity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DataControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void fullFlow_importsEndpoint() throws Exception {
        // Create request
        ImportRequestDTO importRequest = new ImportRequestDTO();
        importRequest.setAmount(15);
        importRequest.setCityExit(ColombianCity.MEDELLIN);
        importRequest.setCityEntry(ColombianCity.CALI);

        // POST /api/imports
        String importJson = objectMapper.writeValueAsString(importRequest);
        String postResponse = mockMvc.perform(
                        post("/api/imports")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(importJson)
                )
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        ImportResponseDTO savedImport = objectMapper.readValue(postResponse, ImportResponseDTO.class);
        assertThat(savedImport.getId()).isNotNull();
        assertThat(savedImport.getAmount()).isEqualTo(15);
        assertThat(savedImport.getCityExit()).isEqualTo("MEDELLIN");
        assertThat(savedImport.getCityEntry()).isEqualTo("CALI");
        assertThat(savedImport.getLat()).isEqualTo(3.4517);
        assertThat(savedImport.getLng()).isEqualTo(-76.5320);

        // GET /api/imports
        String getResponse = mockMvc.perform(get("/api/imports"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ImportResponseDTO[] imports = objectMapper.readValue(getResponse, ImportResponseDTO[].class);
        assertThat(imports).isNotEmpty();
        boolean found = false;
        for (ImportResponseDTO dto : imports) {
            if (dto.getId().equals(savedImport.getId())) {
                found = true;
                break;
            }
        }
        assertThat(found).isTrue();
    }

    @Test
    public void fullFlow_infectionsEndpoint() throws Exception {
        // Create request
        InfectionRequestDTO infectionRequest = new InfectionRequestDTO();
        infectionRequest.setCity(ColombianCity.BOGOTA);
        infectionRequest.setRadius(7.5);
        infectionRequest.setDisease("Malaria");
        infectionRequest.setCases(30);
        infectionRequest.setRegion("Cundinamarca");

        // POST /api/infections
        String infectionJson = objectMapper.writeValueAsString(infectionRequest);
        String postResponse = mockMvc.perform(
                        post("/api/infections")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(infectionJson)
                )
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        InfectionResponseDTO savedInfection = objectMapper.readValue(postResponse, InfectionResponseDTO.class);
        assertThat(savedInfection.getId()).isNotNull();
        assertThat(savedInfection.getCity()).isEqualTo("BOGOTA");
        assertThat(savedInfection.getLat()).isEqualTo(4.7110);
        assertThat(savedInfection.getLng()).isEqualTo(-74.0721);
        assertThat(savedInfection.getRadius()).isEqualTo(7.5);
        assertThat(savedInfection.getDisease()).isEqualTo("Malaria");
        assertThat(savedInfection.getCases()).isEqualTo(30);
        assertThat(savedInfection.getRegion()).isEqualTo("Cundinamarca");

        // GET /api/infections
        String getResponse = mockMvc.perform(get("/api/infections"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        InfectionResponseDTO[] infections = objectMapper.readValue(getResponse, InfectionResponseDTO[].class);
        assertThat(infections).isNotEmpty();
        boolean found = false;
        for (InfectionResponseDTO dto : infections) {
            if (dto.getId().equals(savedInfection.getId())) {
                found = true;
                break;
            }
        }
        assertThat(found).isTrue();
    }
}
