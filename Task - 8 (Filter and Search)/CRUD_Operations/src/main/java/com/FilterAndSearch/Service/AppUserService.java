package com.FilterAndSearch.Service;

import com.FilterAndSearch.DTOs.AppUserDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AppUserService {


    public AppUserDto getAppUserById(Long id);

    public Page<AppUserDto> getAllAppUsers(Integer pageNumber, Integer pageSize);

    public void updateAppUser(int id, AppUserDto appUserDto);

    public void deleteAppUserById(Long id);

}
