package com.FilterAndSearch.Service;

import com.FilterAndSearch.DTOs.AppUserDto;
import com.FilterAndSearch.DTOs.HospitalDto;
import com.FilterAndSearch.DTOs.PatientPaginationDto;
import com.FilterAndSearch.DTOs.StaffPaginationDto;
import com.FilterAndSearch.JwtSecurity.JwtRequest;
import com.FilterAndSearch.JwtSecurity.JwtResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    public void registerUser(AppUserDto appUserDto) throws Exception;

    public JwtResponse login(JwtRequest request);

    public ResponseEntity<?> logout(String tokenPassed);

}
