package com.devopsbuddy.backend.persistance.repositories;

import com.devopsbuddy.backend.persistance.domain.backend.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findById(Integer aLong);
}
