package com.CRUD.Service;

import com.CRUD.DTOs.PaginationResponseDto;
import com.CRUD.DTOs.StaffDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StaffService {

    public void createStaff(StaffDto staffDto);

    public StaffDto getStaffById(Long id);

    public List<StaffDto> getAllStaff(Integer pageNumber, Integer pageSize);

    public Page<StaffDto> getStaffByNameOrPosition(String searchValue, int pageNumber, int pageSize);

    public void updateStaff(StaffDto staffDto);

    public void deleteStaffById(Long id);

}
