package com.CRUD.Service;

import com.CRUD.DTOs.PaginationResponseDto;
import com.CRUD.DTOs.PatientDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PatientService {

    public void createPatient(PatientDto patientDto);


    public PatientDto getPatientById(Long id);

    public List<PatientDto> getAllPatient(Integer pageNumber, Integer pageSize);

    public Page<PatientDto> getPatientByDiagnosis(String searchValue, int pageNumber, int pageSize);

    public void updatePatient(PatientDto patientDto);

    public void deletePatientById(Long id);

}
