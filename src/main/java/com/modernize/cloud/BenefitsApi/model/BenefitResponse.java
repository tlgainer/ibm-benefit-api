package com.modernize.cloud.BenefitsApi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class BenefitResponse {
    private Long id;
    private Long empId;
    private String description;
    @JsonProperty("start_date")
    private LocalDate dateStart;
    @JsonProperty("end_date")
    private LocalDate dateEnd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

}
