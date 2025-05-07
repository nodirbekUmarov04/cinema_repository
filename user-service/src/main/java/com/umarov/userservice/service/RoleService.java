package com.umarov.userservice.service;

import com.umarov.userservice.model.RoleData;
import com.umarov.userservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleData createRole(RoleData role) {
        return roleRepository.save(role);
    }

    public Optional<RoleData> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public Optional<RoleData> getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    public List<RoleData> getAllRoles() {
        return roleRepository.findAll();
    }
}