package com.sharma.pari.project.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sharma.pari.project.model.Disease;
import com.sharma.pari.project.model.Insurance;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@JsonIgnoreProperties(value = "true")
public class PatientDto {

    private String fullName;

    private Disease disease;

    private Insurance insurance;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String admitDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dischargeDate;

    private Boolean isDischarge = false;

    private Province province;
}
