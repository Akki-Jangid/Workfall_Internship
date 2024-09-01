package com.CRUD.DTOs;

import com.CRUD.Model.AppUser;
import com.CRUD.Model.Hospital;
import com.CRUD.Model.Patient;
import com.CRUD.Model.Staff;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StaffDto {


    private Long id;

    @NotBlank(message = "Name is Mandatory")
    private String name;

    @NotBlank(message = "Position is Mandatory")
    private String position;

    @NotNull(message = "Joining Date is Mandatory")
    private Date dateOfJoining;

    @NotBlank(message = "specialization is Mandatory")
    private String specialization;

    @NotNull(message = "AppUser_ID is Mandatory")
    private Long appUserId;

    @NotNull(message = "Hospital_ID is Mandatory")
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
