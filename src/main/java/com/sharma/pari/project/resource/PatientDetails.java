package com.sharma.pari.project.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(value = "true")
public class PatientDetails {
    private String insuranceName;

    private int numberOfPatient;
}
