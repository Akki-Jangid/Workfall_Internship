package com.CRUD.DTOs;

import com.CRUD.Model.Hospital;
import com.CRUD.Model.Patient;
import com.CRUD.Model.Staff;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@Setter
@Getter
public class HospitalDto {

    public  HospitalDto(Hospital hospital){
        this.id = hospital.getId();
        this.name = hospital.getName();
        this.address = hospital.getAddress();
        this.phoneNumber = hospital.getPhoneNumber();
        this.email = hospital.getEmail();
    }

    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
}
