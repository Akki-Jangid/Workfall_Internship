package com.CRUD.DTOs;

import com.CRUD.Model.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
public class PatientDto {

    private Long id;
    private String diagnosis;
    private  Date admittedDate;
    private  String medicalHistory;
    private  Status status;
    private Long appUserId;
    private Long hospitalId;
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
