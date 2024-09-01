package com.CRUD.Controller;

import com.CRUD.DTOs.PaginationResponseDto;
import com.CRUD.DTOs.PatientDto;
import com.CRUD.Service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@Validated
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/save")
    public ResponseEntity<String> createPatient(@Valid  @RequestBody PatientDto patientDto){
        patientService.createPatient(patientDto);
        return new ResponseEntity<>("Patient Created Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/getById")
    public ResponseEntity<PatientDto> getPatientById(@Valid @RequestParam(name = "id") Long id){
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PostMapping("/pagination/getByDiagnosis")
    public ResponseEntity<Page<PatientDto>> getPatientByDiagnosis(@Valid @RequestParam(required = false) String searchValue,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(patientService.getPatientByDiagnosis(searchValue, page, size));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePatient(@Valid @RequestBody PatientDto patientDto){
        patientService.updatePatient(patientDto);
        return new ResponseEntity<>("Patient Updated Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePatient(@Valid @RequestParam(name = "id") Long id){
        patientService.deletePatientById(id);
        return new ResponseEntity<>("Patient Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PatientDto>> getAllPatients(@Valid @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                           @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize){
        return ResponseEntity.ok(patientService.getAllPatient(pageNumber, pageSize));
    }
}
