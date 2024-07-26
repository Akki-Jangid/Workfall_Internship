package com.CRUD.Service;

import com.CRUD.DTOs.AppUserDto;
import com.CRUD.DTOs.PaginationResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AppUserService {

    public void createAppUser(AppUserDto appUserDto);

    public AppUserDto getAppUserById(Long id);

    public List<AppUserDto> getAllAppUsers(Integer pageNumber, Integer pageSize);

    public Page<AppUserDto> getAppUserByNameOrEmail(String searchValue, int pageNumber, int pageSize);

    public void updateAppUser(AppUserDto appUserDto);

    public void deleteAppUserById(Long id);

}
