package com.CRUD.ServiceImpl;

import com.CRUD.DTOs.AppUserDto;
import com.CRUD.DTOs.PaginationResponseDto;
import com.CRUD.Model.AppUser;
import com.CRUD.Model.ResourceNotFoundException;
import com.CRUD.Repository.AppUserRepository;
import com.CRUD.Repository.HospitalRepository;
import com.CRUD.Repository.PatientRepository;
import com.CRUD.Repository.StaffRepository;
import com.CRUD.Service.AppUserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public void createAppUser(AppUserDto appUserDto){
        try{
            AppUser user = new AppUser(appUserDto);
            appUserRepository.save(user);
        } catch (Exception e){
            throw new ResourceNotFoundException("PhoneNumber or Email is already Registered!");
        }
    }

    @Override
    public AppUserDto getAppUserById(Long id){
        AppUser appUser = appUserRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("AppUser is not found with id : "+id));
        return mapToDto(appUser);
    }

    @Override
    public List<AppUserDto> getAllAppUsers(Integer pageNumber, Integer pageSize){

        Pageable p = PageRequest.of(pageNumber, pageSize);
        Page<AppUser> page = appUserRepository.findAll(p);
        List<AppUser> list = page.getContent();

        return list.stream().map(this::mapToDto).toList();
    }

    @Override
    public Page<AppUserDto> getAppUserByNameOrEmail(String searchValue, int pageNumber, int pageSize)
            throws ResourceNotFoundException {

        Specification<AppUser> appUserSpecification = new Specification<AppUser>() {
            @Override
            public Predicate toPredicate(Root<AppUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate namePredicate = cb.like(cb.lower(root.get("username")), "%" + searchValue.toLowerCase() + "%");
                Predicate emailPredicate = cb.like(cb.lower(root.get("email")), "%" + searchValue.toLowerCase() + "%");
                return cb.or(namePredicate, emailPredicate);
            }
        };

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<AppUser> appUserPage = appUserRepository.findAll(appUserSpecification, pageRequest);

        if(appUserPage.isEmpty()) throw new ResourceNotFoundException("No User Found");

        List<AppUserDto> appUserDtoList = appUserPage.stream().map(AppUserDto::new).toList();

        return new PageImpl<>(appUserDtoList, pageRequest, appUserPage.getTotalElements());
    }


    @Override
    public void updateAppUser(AppUserDto appUserDto){
        AppUser appUser = appUserRepository.findById(appUserDto.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("AppUser is not found with id : "+appUserDto.getId()));

        if(appUserDto.getUsername()!=null) appUser.setUsername(appUserDto.getUsername());
        if(appUserDto.getPassword()!=null) appUser.setPassword(appUserDto.getPassword());
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
}
