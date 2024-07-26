package com.CRUD.Model;


import com.CRUD.DTOs.HospitalDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Hospital extends CommonBase {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    public  Hospital(HospitalDto hospitalDto){
        this.name = hospitalDto.getName();
        this.address = hospitalDto.getAddress();
        this.phoneNumber = hospitalDto.getPhoneNumber();
        this.email = hospitalDto.getEmail();
    }
}
