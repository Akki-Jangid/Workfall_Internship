package com.CRUD.Model;

import com.CRUD.DTOs.AppUserDto;
import com.CRUD.DTOs.StaffDto;
import com.sun.management.HotSpotDiagnosticMXBean;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Staff extends CommonBase{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private Date dateOfJoining;

    @Column(nullable = false)
    private String specialization;

    @OneToOne
    @JoinColumn(name = "appUser_id")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "staff")
    private List<Patient> patients;

    public Staff(StaffDto staffDto, AppUser appUser, Hospital hospital){
        this.position = staffDto.getPosition();
        this.dateOfJoining = staffDto.getDateOfJoining();
        this.specialization = staffDto.getSpecialization();
        this.appUser = appUser;
        this.hospital = hospital;
    }

}
