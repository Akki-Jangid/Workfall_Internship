package com.CRUD.Controller;

import com.CRUD.DTOs.PaginationResponseDto;
import com.CRUD.DTOs.StaffDto;
import com.CRUD.Service.StaffService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
@Validated
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping("/save")
    public ResponseEntity<String> createStaff(@Valid @RequestBody StaffDto staffDto){
        staffService.createStaff(staffDto);
        return new ResponseEntity<>("Staff Created Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/getById")
    public ResponseEntity<StaffDto> getStaffDetailsById(@Valid @RequestParam(name = "id") Long id){
        return ResponseEntity.ok(staffService.getStaffById(id));
    }

    @PostMapping("/pagination/getByNameOrPosition")
    public ResponseEntity<Page<StaffDto>> getStaffByNameOrPosition(@Valid @RequestParam(required = false) String searchValue,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(staffService.getStaffByNameOrPosition(searchValue, page, size));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateStaffInfo( @Valid @RequestBody StaffDto staffDto){
        staffService.updateStaff(staffDto);
        return new ResponseEntity<>("Staff Updated Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteStaff(@Valid @RequestParam(name = "id") Long id){
        staffService.deleteStaffById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StaffDto>> getAllStaff(@Valid @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                      @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize){
        return ResponseEntity.ok(staffService.getAllStaff(pageNumber, pageSize));
    }
}
