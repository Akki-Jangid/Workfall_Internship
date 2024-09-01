package com.CRUD.Controller;

import com.CRUD.DTOs.HospitalDto;
import com.CRUD.DTOs.PaginationResponseDto;
import com.CRUD.Service.HospitalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/hospital")
@Validated
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping("/save")
    public ResponseEntity<String> createHospital(@Valid  @RequestBody HospitalDto hospitalDto){
        hospitalService.createHospital(hospitalDto);
        return new ResponseEntity<>("Hospital Created Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/getById")
    public ResponseEntity<HospitalDto> getHospitalInfoById(@Valid @RequestParam(name = "id") Long id){
        return ResponseEntity.ok(hospitalService.getHospitalById(id));
    }

    @PostMapping("/pagination/getByNameOrAddress")
    public ResponseEntity<Page<HospitalDto>> getHospitalByNameAndAddress(@Valid @RequestParam(required = false) String searchValue,
                                                                         @RequestParam(defaultValue = "0") int page,
                                                                         @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(hospitalService.getHospitalByNameOrAddress(searchValue, page, size));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateHospitalInfo(@Valid @RequestBody HospitalDto hospitalDto){
        hospitalService.updateHospital(hospitalDto);
        return new ResponseEntity<>("Hospital Updated Created Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteHospitalInfo(@Valid @RequestParam(name = "id") Long id){
        hospitalService.deleteHospitalById(id);
        return new ResponseEntity<>("Hospital Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<HospitalDto>> getAllAppUsers(@Valid @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize){
        return ResponseEntity.ok(hospitalService.getAllHospitals(pageNumber, pageSize));
    }
}
