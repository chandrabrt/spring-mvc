package com.sharma.pari.project.repository;

import com.sharma.pari.project.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRespository extends JpaRepository<Role, Integer> {

    Role findByRole(String role);
}
