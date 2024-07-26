package com.CRUD.Model;

import com.CRUD.DTOs.PatientDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Patient extends CommonBase {

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private String diagnosis;

    @Column(nullable = false)
    private Date admittedDate;

    @Column(nullable = false)
    private String medicalHistory;


    @OneToOne
    @JoinColumn(name = "appUser_id")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    public  Patient(PatientDto patientDto, AppUser appUser, Hospital hospital){
        this.status = patientDto.getStatus();
        this.diagnosis = patientDto.getDiagnosis();
        this.admittedDate = patientDto.getAdmittedDate();
        this.medicalHistory = patientDto.getMedicalHistory();
        this.appUser = appUser;
        this.hospital = hospital;
    }
}
