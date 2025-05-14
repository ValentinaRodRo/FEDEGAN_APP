package com.backend.dto;

import com.backend.model.ColombianCity;
import lombok.Data;

@Data
public class ImportRequestDTO {
    private Integer amount;
    private ColombianCity cityExit;
    private ColombianCity cityEntry;
}
