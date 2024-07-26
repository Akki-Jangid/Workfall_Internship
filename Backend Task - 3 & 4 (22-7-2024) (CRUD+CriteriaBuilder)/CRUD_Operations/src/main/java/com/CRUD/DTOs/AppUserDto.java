package com.CRUD.DTOs;

import com.CRUD.Model.AppUser;
import com.CRUD.Model.Patient;
import com.CRUD.Model.Role;
import com.CRUD.Model.Staff;
import jakarta.persistence.OneToOne;
import lombok.*;

@NoArgsConstructor
@Setter
@Getter
public class AppUserDto {

    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private Role role;
    private String password;

    public AppUserDto(AppUser appUser){
        this.id = appUser.getId();
        this.username = appUser.getUsername();
        this.password = appUser.getPassword();
        this.email = appUser.getEmail();
        this.phoneNumber = appUser.getPhoneNumber();
        this.role = appUser.getRole();
    }
}
