package com.CRUD.Controller;

import com.CRUD.DTOs.AppUserDto;
import com.CRUD.DTOs.PaginationResponseDto;
import com.CRUD.Model.ResourceNotFoundException;
import com.CRUD.Service.AppUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appUser")
@Validated
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/save")
    public ResponseEntity<String> createAppUser(@Valid @RequestBody AppUserDto appUserDto){
        appUserService.createAppUser(appUserDto);
        return new ResponseEntity<>("AppUser Created Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/getById")
    public ResponseEntity<AppUserDto> getAppUserById(@Valid @RequestParam(name = "id") Long id){
        return ResponseEntity.ok(appUserService.getAppUserById(id));
    }

    @PostMapping("/pagination/getByNameOrEmail")
    public ResponseEntity<Page<AppUserDto>> getAppUserByCriteria( @Valid
                                                                  @RequestParam(required = false) String searchValue,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size){
       return ResponseEntity.ok(appUserService.getAppUserByNameOrEmail(searchValue, page, size));
    }


    @PutMapping("/update")
    public ResponseEntity<String> updateAppUser(@Valid @RequestBody AppUserDto appUserDto){
        appUserService.updateAppUser(appUserDto);
        return new ResponseEntity<>("AppUser Updated Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAppUser(@RequestParam(name = "id") Long id){
        appUserService.deleteAppUserById(id);
        return new ResponseEntity<>("AppUser Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AppUserDto>> getAllAppUsers(
                        @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                        @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize){
        return ResponseEntity.ok(appUserService.getAllAppUsers(pageNumber, pageSize));
    }
}
