package com.CRUD.DTOs;

import com.CRUD.Model.AppUser;
import com.CRUD.Model.Hospital;
import com.CRUD.Model.Patient;
import com.CRUD.Model.Staff;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@NoArgsConstructor
@Setter
@Getter
public class StaffDto {


    private Long id;
    private String name;
    private String position;
    private Date dateOfJoining;
    private String specialization;
    private Long appUserId;
    private Long hospitalId;

    private AppUser appUser;
    private  Hospital hospital;

    public StaffDto(Staff staff){
        this.id = staff.getId();
        this.name = staff.getName();
        this.position = staff.getPosition();
        this.dateOfJoining = staff.getDateOfJoining();
        this.specialization = staff.getSpecialization();
        this.appUser = staff.getAppUser();
        this.hospital = staff.getHospital();
        this.appUserId = staff.getAppUser().getId();
        this.hospitalId = staff.getHospital().getId();
    }
}
