package com.bookinventorymanagement.service;

 

 

import com.bookinventorymanagement.dto.PermRoleDto;
import com.bookinventorymanagement.dto.ResponseDto;

 

 

public interface PermRoleService {

 

    public ResponseDto savePermRole(PermRoleDto permRoleDto);

    public PermRoleDto getPermRoleById(Integer id);

    public PermRoleDto updatePermRoleById(Integer id,PermRoleDto permRoleDto);

    

}
