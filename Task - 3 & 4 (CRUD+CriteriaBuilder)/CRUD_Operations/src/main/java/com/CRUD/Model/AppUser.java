package com.CRUD.Model;

import com.CRUD.DTOs.AppUserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class AppUser extends  CommonBase {

    @Column(nullable = false, unique = true)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    public AppUser(AppUserDto appUserDto){
        this.username = appUserDto.getUsername();
        this.password = appUserDto.getPassword();
        this.email = appUserDto.getEmail();
        this.phoneNumber = appUserDto.getPhoneNumber();
        this.role = appUserDto.getRole();
    }
}
