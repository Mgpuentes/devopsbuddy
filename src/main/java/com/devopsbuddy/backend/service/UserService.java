package com.devopsbuddy.backend.service;

import com.devopsbuddy.backend.persistance.domain.backend.Plan;
import com.devopsbuddy.backend.persistance.domain.backend.User;
import com.devopsbuddy.backend.persistance.domain.backend.UserRole;
import com.devopsbuddy.backend.persistance.repositories.PlanRepository;
import com.devopsbuddy.backend.persistance.repositories.RoleRepository;
import com.devopsbuddy.backend.persistance.repositories.UserRepository;
import com.devopsbuddy.enums.PlansEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final RoleRepository roleRepository;

    private final PlanRepository planRepository;

    private final UserRepository userRepository;

    @Autowired
    public UserService(RoleRepository roleRepository, PlanRepository planRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(User user, PlansEnum plansEnum, Set<UserRole> userRoles) {
        Plan plan = new Plan(plansEnum);

        //make sure the plan exist in the database
        if (!planRepository.existsById(plansEnum.getId())) {
            plan = planRepository.save(plan);
        }

        user.setPlan(plan);

        for (UserRole ur : userRoles) {
            roleRepository.save(ur.getRole());
        }

        //adding all possible user roles for user being created
        user.getUserRoles().addAll(userRoles);

        user = userRepository.save(user);

        return user;
    }
}
