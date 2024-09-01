package com.CRUD.ServiceImpl;

import com.CRUD.DTOs.AppUserDto;
import com.CRUD.DTOs.HospitalDto;
import com.CRUD.DTOs.PaginationResponseDto;
import com.CRUD.Model.AppUser;
import com.CRUD.Model.Hospital;
import com.CRUD.Model.ResourceNotFoundException;
import com.CRUD.Repository.AppUserRepository;
import com.CRUD.Repository.HospitalRepository;
import com.CRUD.Repository.PatientRepository;
import com.CRUD.Repository.StaffRepository;
import com.CRUD.Service.HospitalService;
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
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public void createHospital(HospitalDto hospitalDto){
        try{
            Hospital hospital = new Hospital(hospitalDto);
            hospitalRepository.save(hospital);
        }
        catch (Exception e){
            throw new ResourceNotFoundException("Hospital is already Registered with this Email or PhoneNumber!");
        }
    }

    @Override
    public HospitalDto getHospitalById(Long id){
        Hospital hospital = hospitalRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Hospital is not found with id : "+id));
        return mapToDto(hospital);
    }

    @Override
    public List<HospitalDto> getAllHospitals(Integer pageNumber, Integer pageSize){

        Pageable p = PageRequest.of(pageNumber, pageSize);
        Page<Hospital> page = hospitalRepository.findAll(p);
        List<Hospital> list = page.getContent();

        return list.stream().map(this::mapToDto).toList();
    }

    @Override
    public Page<HospitalDto> getHospitalByNameOrAddress(String searchValue, int pageNumber, int pageSize) {

        Specification<Hospital> hospitalSpecification = new Specification<Hospital>() {
            @Override
            public Predicate toPredicate(Root<Hospital> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate namePredicate = cb.like(cb.lower(root.get("name")), "%" + searchValue.toLowerCase() + "%");
                Predicate addressPredicate = cb.like(cb.lower(root.get("address")), "%" + searchValue.toLowerCase() + "%");
                return cb.or(namePredicate, addressPredicate);
            }
        };

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Hospital> hospitalPage = hospitalRepository.findAll(hospitalSpecification, pageRequest);

        if(hospitalPage.isEmpty()) throw new ResourceNotFoundException("No Hospital Found" +
                "");

        List<HospitalDto> hospitalDtoList = hospitalPage.stream().map(HospitalDto::new).toList();

        return new PageImpl<>(hospitalDtoList, pageRequest, hospitalPage.getTotalElements());
    }

    @Override
    public void updateHospital(HospitalDto hospitalDto){
        Hospital hospital = hospitalRepository.findById(hospitalDto.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("Hospital is not found with id : "+hospitalDto.getId()));

        if(hospitalDto.getName()!=null) hospital.setName((hospitalDto.getName()));
        if(hospitalDto.getEmail()!=null) hospital.setEmail(hospitalDto.getEmail());
        if(hospitalDto.getAddress()!=null)  hospital.setAddress(hospitalDto.getAddress());
        if(hospitalDto.getPhoneNumber()!=null)  hospital.setPhoneNumber(hospitalDto.getPhoneNumber());

        hospitalRepository.save(hospital);
    }

    @Override
    public void deleteHospitalById(Long id){
        Hospital hospital = hospitalRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Hospital is not found with id : "+id));
        hospitalRepository.delete(hospital);
    }

    public HospitalDto mapToDto(Hospital hospital) {
        return new HospitalDto(hospital);

    }
}
