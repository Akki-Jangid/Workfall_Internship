package com.CRUD.DTOs;

import com.CRUD.Model.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
public class PatientDto {

    private Long id;

    @NotBlank(message = "Diagnosis is Mandatory")
    private String diagnosis;

    @NotNull(message = "Admitted Date is Mandatory")
    private  Date admittedDate;

    @NotBlank(message = "Medical History Mandatory")
    private  String medicalHistory;

    @NotNull(message = "Status is Mandatory")
    private  Status status;

    @NotNull(message = "AppUser_ID is Mandatory")
    private Long appUserId;
    @NotNull(message = "Hospital_ID is Mandatory")
    private Long hospitalId;
    @NotNull(message = "Staff_ID is Mandatory")
    private Long staffId;

    private AppUser appUser;
    private Hospital hospital;

    public  PatientDto(Patient patient){
        this.id = patient.getId();
        this.status = patient.getStatus();
        this.diagnosis = patient.getDiagnosis();
        this.admittedDate = patient.getAdmittedDate();
        this.medicalHistory = patient.getMedicalHistory();
        this.appUser = patient.getAppUser();
        this.hospital = patient.getHospital();
        this.appUserId = patient.getAppUser().getId();
        this.hospitalId = patient.getHospital().getId();
        this.staffId = patient.getStaff().getId();
    }

}
