package com.FilterAndSearch.ServiceImpl;

import com.FilterAndSearch.DTOs.AppUserDto;
import com.FilterAndSearch.Model.AppUser;
import com.FilterAndSearch.Model.ResourceNotFoundException;
import com.FilterAndSearch.Repository.AppUserRepository;
import com.FilterAndSearch.Repository.HospitalRepository;
import com.FilterAndSearch.Repository.PatientRepository;
import com.FilterAndSearch.Repository.StaffRepository;
import com.FilterAndSearch.Service.AppUserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Override
//    public void createAppUser(AppUserDto appUserDto){
//        try{
//            AppUser user = new AppUser(appUserDto);
//            appUserRepository.save(user);
//        } catch (Exception e){
//            throw new ResourceNotFoundException("PhoneNumber or Email is already Registered!");
//        }
//    }

    @Override
    public AppUserDto getAppUserById(Long id){
        AppUser appUser = appUserRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("AppUser is not found with id : "+id));
        return mapToDto(appUser);
    }

    @Override
    public Page<AppUserDto> getAllAppUsers(Integer pageNumber, Integer pageSize){

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<AppUser> appUserPage = appUserRepository.findAll(pageable);
        List<AppUserDto> list = appUserPage.stream().map(this::mapToDto).toList();

        return new PageImpl<>(list, pageable, appUserPage.getTotalElements());
    }

    @Override
    public void updateAppUser(int id, AppUserDto appUserDto){
        AppUser appUser = appUserRepository.findById((long) id).orElseThrow(
                ()-> new ResourceNotFoundException("AppUser is not found with id : "+id));

        if(appUserDto.getUsername()!=null) appUser.setUsername(appUserDto.getUsername());
        if(appUserDto.getPassword()!=null) {
            appUser.setPassword(passwordEncoder.encode(appUserDto.getPassword()));
        }
        if(appUserDto.getEmail()!=null)  appUser.setEmail(appUserDto.getEmail());
        if(appUserDto.getRole()!=null)  appUser.setRole(appUserDto.getRole());

        appUserRepository.save(appUser);
    }

    @Override
    public void deleteAppUserById(Long id){
        AppUser appUser = appUserRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("AppUser is not found with id : "+id));
        appUserRepository.delete(appUser);
    }

    public AppUserDto mapToDto(AppUser appUser) {
        return new AppUserDto(appUser);
    }


//    @Override
//    public Page<AppUserDto> getAppUserByNameOrEmail(String searchValue, int pageNumber, int pageSize)
//            throws ResourceNotFoundException {
//        return new PageImpl<>(appUserDtoList, pageRequest, appUserPage.getTotalElements());

//    }

}
