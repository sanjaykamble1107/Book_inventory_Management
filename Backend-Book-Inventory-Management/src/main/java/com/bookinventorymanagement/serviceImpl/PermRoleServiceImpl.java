package com.bookinventorymanagement.serviceImpl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookinventorymanagement.dto.PermRoleDto;
import com.bookinventorymanagement.dto.ResponseDto;
import com.bookinventorymanagement.entity.PermRole;
import com.bookinventorymanagement.exceptions.PermRoleNotFoundException;
import com.bookinventorymanagement.repository.PermRoleRepository;
import com.bookinventorymanagement.service.PermRoleService;

@Service
public class PermRoleServiceImpl implements PermRoleService {

	@Autowired
	private PermRoleRepository permRoleRepository;

	private ResponseDto responseDto = new ResponseDto();

	/**
	 * Save a permission role.
	 *
	 * @param permRoleDto The PermRoleDto containing the permission role details to be saved.
	 * @return The ResponseDto with a success message.
	 * @throws PermRoleNotFoundException if a permission role with the same role number already exists.
	 */
	@Override
	public ResponseDto savePermRole(PermRoleDto permRoleDto) {
		Optional<PermRole> optional = permRoleRepository.findById(permRoleDto.getRoleNumber());
		if (optional.isPresent()) {
			throw new PermRoleNotFoundException("Permission role is already present with RoleNumber: " + permRoleDto.getRoleNumber());
		}

		PermRole permRole = new PermRole();
		permRole.setRollNumber(permRoleDto.getRoleNumber());
		permRole.setPermRole(permRoleDto.getPermRole());
		responseDto.setResponseMessage("Permission role added successfully");
		permRoleRepository.save(permRole);
		return responseDto;
	}

	/**
	 * Update a permission role by its role number.
	 *
	 * @param rollNumber   The role number of the permission role.
	 * @param permRoleDto  The PermRoleDto containing the updated permission role details.
	 * @return The PermRoleDto containing the updated permission role details.
	 * @throws PermRoleNotFoundException if the permission role with the given role number is not found.
	 */
	@Override
	public PermRoleDto updatePermRoleById(Integer rollNumber, PermRoleDto permRoleDto) {

		Optional<PermRole> optionalPermRole = permRoleRepository.findById(rollNumber);
		if (optionalPermRole.isPresent()) {
			PermRole permRole = optionalPermRole.get();

			permRole.setPermRole(permRoleDto.getPermRole());

			permRoleRepository.save(permRole);

			PermRoleDto dto = new PermRoleDto();
			BeanUtils.copyProperties(permRole, dto);
			dto.setRoleNumber(permRole.getRollNumber());

			return dto;
		}
		throw new PermRoleNotFoundException("Permission role not found with ID: " + rollNumber);

	}

	/**
	 * Get a permission role by its role number.
	 *
	 * @param rollNumber The role number of the permission role.
	 * @return The PermRoleDto containing the permission role details.
	 * @throws PermRoleNotFoundException if the permission role with the given role number is not found.
	 */
	@Override
	public PermRoleDto getPermRoleById(Integer rollNumber) {
		Optional<PermRole> findById = permRoleRepository.findById(rollNumber);
		if (findById.isPresent()) {
			PermRoleDto permRoleDto = new PermRoleDto();
			permRoleDto.setRoleNumber(findById.get().getRollNumber());
			BeanUtils.copyProperties(findById.get(), permRoleDto);
			return permRoleDto;
		}
		throw new PermRoleNotFoundException("Permission role not found with ID: " + rollNumber);
	}

}

