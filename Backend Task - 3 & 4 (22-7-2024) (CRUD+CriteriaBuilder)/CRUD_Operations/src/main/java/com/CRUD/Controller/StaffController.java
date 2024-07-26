package com.CRUD.Controller;

import com.CRUD.DTOs.PaginationResponseDto;
import com.CRUD.DTOs.StaffDto;
import com.CRUD.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping("/save")
    public ResponseEntity<String> createStaff(@RequestBody StaffDto staffDto){
        staffService.createStaff(staffDto);
        return new ResponseEntity<>("Staff Created Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/getById")
    public ResponseEntity<StaffDto> getStaffDetailsById(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok(staffService.getStaffById(id));
    }

    @PostMapping("/pagination/getByNameOrPosition")
    public ResponseEntity<Page<StaffDto>> getStaffByNameOrPosition(@RequestParam(required = false) String searchValue,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(staffService.getStaffByNameOrPosition(searchValue, page, size));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateStaffInfo(@RequestBody StaffDto staffDto){
        staffService.updateStaff(staffDto);
        return new ResponseEntity<>("Staff Updated Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteStaff(@RequestParam(name = "id") Long id){
        staffService.deleteStaffById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StaffDto>> getAllStaff(@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                                                      @RequestParam(value = "pageSize", defaultValue = "2", required = false) Integer pageSize){
        return ResponseEntity.ok(staffService.getAllStaff(pageNumber, pageSize));
    }
}
