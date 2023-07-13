package com.bookinventorymanagement.controller;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookinventorymanagement.dto.PermRoleDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.service.PermRoleService;

import jakarta.validation.Valid;

@CrossOrigin(origins="http://localhost:4200/")

@RestController
@RequestMapping("/permrole")
@Validated
public class PermRoleController {

    @Autowired
    private PermRoleService permRoleService;

    // Save a permission role
    @PostMapping("/post")
    public ResponseEntity<ResponseDto> savePermRole(@Valid @RequestBody PermRoleDto permRoleDto) {
        ResponseDto dto = permRoleService.savePermRole(permRoleDto);
        return new ResponseEntity<ResponseDto>(dto, HttpStatus.OK);
    }

    // Get a permission role by roll number
    @GetMapping("/{rollNumber}")
    public ResponseEntity<PermRoleDto> getPermRoleById(@PathVariable Integer rollNumber) {
        PermRoleDto permRoleDto = permRoleService.getPermRoleById(rollNumber);
        return new ResponseEntity<PermRoleDto>(permRoleDto, HttpStatus.OK);
    }

    // Update a permission role by roll number
    @PutMapping("/update/permrole/{rollNumber}")
    public ResponseEntity<PermRoleDto> updatePermRoleById(@PathVariable Integer rollNumber, @RequestBody PermRoleDto permRoleDto) {
        PermRoleDto permRoleDtos = permRoleService.updatePermRoleById(rollNumber, permRoleDto);
        return new ResponseEntity<PermRoleDto>(permRoleDtos, HttpStatus.OK);
    }
}
