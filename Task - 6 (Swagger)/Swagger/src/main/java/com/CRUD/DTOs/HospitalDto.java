package com.CRUD.DTOs;

import com.CRUD.Model.Hospital;
import com.CRUD.Model.Patient;
import com.CRUD.Model.Staff;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@Setter
@Getter
public class HospitalDto {

    private Long id;

    @NotBlank(message = "Hospital name is Mandatory")
    private String name;

    @NotBlank(message = "Address is Mandatory")
    private String address;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Phone number is invalid")
    private String phoneNumber;

    @NotBlank(message = "Email is Mandatory")
    @Email(message = "Enter Valid Email")
    private String email;

    public  HospitalDto(Hospital hospital){
        this.id = hospital.getId();
        this.name = hospital.getName();
        this.address = hospital.getAddress();
        this.phoneNumber = hospital.getPhoneNumber();
        this.email = hospital.getEmail();
    }


}
