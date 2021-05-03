package com.rapisolver.api.config;

import com.rapisolver.api.entities.Role;
import com.rapisolver.api.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {
    private final RoleRepository roleRepository;

    @Autowired
    public DataLoader(RoleRepository roleRepository){
        this.roleRepository=roleRepository;
        this.loadData();
    }

    private void loadData() {
        this.addRoles();
    }

    private void addRoles() {
        this.roleRepository.save(new Role("ROLE_CUSTOMER"));
        this.roleRepository.save(new Role("ROLE_SUPPLIER"));
    }
}
