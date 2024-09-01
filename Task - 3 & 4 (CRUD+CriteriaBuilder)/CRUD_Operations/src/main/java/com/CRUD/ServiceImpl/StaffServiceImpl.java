package com.CRUD.ServiceImpl;

import com.CRUD.DTOs.AppUserDto;
import com.CRUD.DTOs.PaginationResponseDto;
import com.CRUD.DTOs.StaffDto;
import com.CRUD.Model.AppUser;
import com.CRUD.Model.Hospital;
import com.CRUD.Model.ResourceNotFoundException;
import com.CRUD.Model.Staff;
import com.CRUD.Repository.AppUserRepository;
import com.CRUD.Repository.HospitalRepository;
import com.CRUD.Repository.StaffRepository;
import com.CRUD.Service.StaffService;
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

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public void createStaff(StaffDto staffDto){
        AppUser appUser = appUserRepository.findById(staffDto.getAppUserId()).orElseThrow();
        Hospital hospital = hospitalRepository.findById(staffDto.getHospitalId()).orElseThrow();
        Staff staff = new Staff(staffDto, appUser, hospital);
        staffRepository.save(staff);
    }

    @Override
    public StaffDto getStaffById(Long id){
        Staff staff = staffRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Staff is not found with id : "+id));
        return mapToDto(staff);
    }

    @Override
    public List<StaffDto> getAllStaff(Integer pageNumber, Integer pageSize){

        Pageable p = PageRequest.of(pageNumber, pageSize);
        Page<Staff> page = staffRepository.findAll(p);
        List<Staff> list = page.getContent();

        return list.stream().map(this::mapToDto).toList();
    }

    @Override
    public Page<StaffDto> getStaffByNameOrPosition(String searchValue, int pageNumber, int pageSize) {
        Specification<Staff> staffSpecification = new Specification<Staff>() {
            @Override
            public Predicate toPredicate(Root<Staff> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate namePredicate = cb.like(cb.lower(root.get("name")), "%" + searchValue.toLowerCase() + "%");
                Predicate positionPredicate = cb.like(cb.lower(root.get("position")), "%" + searchValue.toLowerCase() + "%");
                return cb.or(namePredicate, positionPredicate);
            }
        };

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Staff> staffPage = staffRepository.findAll(staffSpecification, pageRequest);

        List<StaffDto> staffDtoList = staffPage.stream().map(StaffDto::new).toList();

        return new PageImpl<>(staffDtoList, pageRequest, staffPage.getTotalElements());
    }

    @Override
    public void updateStaff(StaffDto staffDto){

        Staff staff = staffRepository.findById(staffDto.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("Staff is not found with id : "+staffDto.getId()));
        AppUser appUser = appUserRepository.findById(staffDto.getAppUserId()).orElseThrow();
        Hospital hospital = hospitalRepository.findById(staffDto.getHospitalId()).orElseThrow();

        if(staffDto.getDateOfJoining()!=null)  staff.setDateOfJoining(staffDto.getDateOfJoining());
        if(staffDto.getSpecialization()!=null) staff.setSpecialization(staffDto.getSpecialization());
        if(staffDto.getPosition()!=null) staff.setPosition(staffDto.getPosition());
        if(staffDto.getHospitalId()!=null) staff.setHospital(hospital);
        if(staffDto.getAppUserId()!=null) staff.setAppUser(appUser);

       staffRepository.save(staff);
    }

    @Override
    public void deleteStaffById(Long id){
        Staff staff = staffRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Staff is not found with id : "+id));
        staffRepository.delete(staff);
    }

    public StaffDto mapToDto(Staff staff) {
        return new StaffDto(staff);
    }
}
