package com.CRUD.Service;

import com.CRUD.DTOs.HospitalDto;
import com.CRUD.DTOs.PaginationResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HospitalService {

    public void createHospital(HospitalDto hospitalDto);

    public HospitalDto getHospitalById(Long id);

    public List<HospitalDto> getAllHospitals(Integer pageNumber, Integer pageSize);

    public Page<HospitalDto> getHospitalByNameOrAddress(String searchValue, int pageNumber, int pageSize);

    public void updateHospital(HospitalDto hospitalDto);

    public void deleteHospitalById(Long id);

}
