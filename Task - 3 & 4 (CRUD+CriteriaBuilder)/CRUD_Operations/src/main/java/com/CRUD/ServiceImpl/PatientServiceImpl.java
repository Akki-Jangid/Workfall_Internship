package com.CRUD.ServiceImpl;

import com.CRUD.DTOs.AppUserDto;
import com.CRUD.DTOs.PaginationResponseDto;
import com.CRUD.DTOs.PatientDto;
import com.CRUD.Model.*;
import com.CRUD.Repository.AppUserRepository;
import com.CRUD.Repository.HospitalRepository;
import com.CRUD.Repository.PatientRepository;
import com.CRUD.Repository.StaffRepository;
import com.CRUD.Service.PatientService;
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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public void createPatient(PatientDto patientDto){
        AppUser appUser = appUserRepository.findById(patientDto.getAppUserId()).orElseThrow();
        Hospital hospital = hospitalRepository.findById(patientDto.getHospitalId()).orElseThrow();

        Patient patient = new Patient(patientDto, appUser, hospital);
        patientRepository.save(patient);
    }


    @Override
    public PatientDto getPatientById(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Patient is not found with id : "+id));
        return mapToDto(patient);
    }

    @Override
    public List<PatientDto> getAllPatient(Integer pageNumber, Integer pageSize){
        Pageable p = PageRequest.of(pageNumber, pageSize);
        Page<Patient> page = patientRepository.findAll(p);
        List<Patient> list = page.getContent();

        return list.stream().map(this::mapToDto).toList();
    }

    @Override
    public Page<PatientDto> getPatientByDiagnosis(String searchValue, int pageNumber, int pageSize) {
        Specification<Patient> patientSpecification = new Specification<Patient>() {
            @Override
            public Predicate toPredicate(Root<Patient> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(cb.lower(root.get("diagnosis")), "%" + searchValue.toLowerCase() + "%");
            }
        };

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Patient> patientPage = patientRepository.findAll(patientSpecification, pageRequest);

        List<PatientDto> patientDtoList = patientPage.stream().map(PatientDto::new).toList();

        return new PageImpl<>(patientDtoList, pageRequest, patientPage.getTotalElements());
    }

    @Override
    public void updatePatient(PatientDto patientDto){

        Patient patient = patientRepository.findById(patientDto.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("Patient is not found with id : "+patientDto.getId()));

        AppUser appUser = appUserRepository.findById(patientDto.getAppUserId()).orElseThrow();
        Hospital hospital = hospitalRepository.findById(patientDto.getHospitalId()).orElseThrow();
        Staff staff = staffRepository.findById(patientDto.getStaffId()).orElseThrow();

        if(patientDto.getStatus()!=null) patient.setStatus(patientDto.getStatus());
        if(patientDto.getMedicalHistory()!=null) patient.setMedicalHistory(patientDto.getMedicalHistory());
        if(patientDto.getAdmittedDate()!=null) patient.setAdmittedDate(patientDto.getAdmittedDate());
        if(patientDto.getHospitalId()!=null) patient.setHospital(hospital);
        if(patientDto.getAppUserId()!=null)patient.setAppUser(appUser);

        patientRepository.save(patient);
    }

    @Override
    public void deletePatientById(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Patient is not found with id : "+id));
        patientRepository.delete(patient);
    }

    public PatientDto mapToDto(Patient patient) {
        return new PatientDto(patient);
    }
}
