package com.sharma.pari.project.resource;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(value = "true")
public class PatientDisease {
    private int diseaseId;
    private String name;
    private Long count;

    public PatientDisease(int diseaseId, String name, Long count) {
        this.diseaseId = diseaseId;
        this.name = name;
        this.count = count;
    }
}
