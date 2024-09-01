package com.FilterAndSearch.Service;

import com.FilterAndSearch.DTOs.HospitalDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HospitalService {

    public void createHospital(HospitalDto hospitalDto);

    public HospitalDto getHospitalById(Long id);

    public Page<HospitalDto> getAllHospitals(Integer pageNumber, Integer pageSize);

    public Page<HospitalDto> getHospitalByNameOrAddress(String searchValue, int pageNumber, int pageSize);

    public void updateHospital(int id, HospitalDto hospitalDto);

    public void deleteHospitalById(Long id);

}
