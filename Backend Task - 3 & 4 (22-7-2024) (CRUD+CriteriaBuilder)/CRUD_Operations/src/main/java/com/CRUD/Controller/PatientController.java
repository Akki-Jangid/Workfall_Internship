package com.CRUD.Controller;

import com.CRUD.DTOs.PaginationResponseDto;
import com.CRUD.DTOs.PatientDto;
import com.CRUD.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/save")
    public ResponseEntity<String> createPatient(@RequestBody PatientDto patientDto){
        patientService.createPatient(patientDto);
        return new ResponseEntity<>("Patient Created Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/getById")
    public ResponseEntity<PatientDto> getPatientById(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PostMapping("/pagination/getByDiagnosis")
    public ResponseEntity<PaginationResponseDto<PatientDto>> getHospitalByDiagnosis(@RequestParam(required = false) String searchValue,
                                                                                    @RequestParam(defaultValue = "0") int page,
                                                                                    @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(patientService.getPatientByDiagnosis(searchValue, page, size));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePatient(@RequestBody PatientDto patientDto){
        patientService.updatePatient(patientDto);
        return new ResponseEntity<>("Patient Updated Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePatient(@RequestParam(name = "id") Long id){
        patientService.deletePatientById(id);
        return new ResponseEntity<>("Patient Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PatientDto>> getAllPatients(@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                                                           @RequestParam(value = "pageSize", defaultValue = "2", required = false) Integer pageSize){
        return ResponseEntity.ok(patientService.getAllPatient(pageNumber, pageSize));
    }
}
