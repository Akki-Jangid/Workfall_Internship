package com.FilterAndSearch.ServiceImpl;

import com.FilterAndSearch.DTOs.AppUserDto;
import com.FilterAndSearch.DTOs.HospitalDto;
import com.FilterAndSearch.DTOs.PatientPaginationDto;
import com.FilterAndSearch.DTOs.StaffPaginationDto;
import com.FilterAndSearch.JwtSecurity.JwtHelper;
import com.FilterAndSearch.JwtSecurity.JwtRequest;
import com.FilterAndSearch.JwtSecurity.JwtResponse;
import com.FilterAndSearch.Model.*;
import com.FilterAndSearch.Repository.*;
import com.FilterAndSearch.Service.UserService;
import com.FilterAndSearch.Token.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private AppUserRepository appUserRepository;



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtHelper helper;


    @Override
    public void registerUser(AppUserDto appUserDto) throws ResourceNotFoundException {

        if (appUserRepository.findByUsername(appUserDto.getUsername()).isPresent())
            throw new ResourceNotFoundException("Username is already taken, Choose something else");

        try{
            appUserDto.setPassword(passwordEncoder.encode(appUserDto.getPassword()));
            AppUser user = new AppUser(appUserDto);
            appUserRepository.save(user);

        } catch (Exception e){
            throw new ResourceNotFoundException("PhoneNumber or Email is already Registered!");
        }
    }

    @Override
    public JwtResponse login(JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());

        String token = this.helper.generateToken(userDetails);

        // This is Used to Store the Token in DB
        tokenRepository.save(new Token(token, userDetails.getUsername()));

        JwtResponse response = JwtResponse.builder().jwtToken(token).username(userDetails.getUsername()).build();
        return response;
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            this.manager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

    @Override
    public ResponseEntity<?> logout(String tokenPassed) {
        String token = tokenPassed.replace("Bearer ", "");
        logger.info("Token is " + token);

        if (!tokenRepository.existsByToken(token)) {
            throw new ResourceNotFoundException("No User Logged In");
        }
        tokenRepository.deleteByToken(token);
        return ResponseEntity.status(HttpStatus.OK).body("Logged out Successfully");
    }
}


