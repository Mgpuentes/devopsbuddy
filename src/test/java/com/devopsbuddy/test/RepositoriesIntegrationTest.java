package com.devopsbuddy.test;

import com.devopsbuddy.backend.persistance.domain.backend.Plan;
import com.devopsbuddy.backend.persistance.domain.backend.Role;
import com.devopsbuddy.backend.persistance.domain.backend.User;
import com.devopsbuddy.backend.persistance.domain.backend.UserRole;
import com.devopsbuddy.backend.persistance.repositories.PlanRepository;
import com.devopsbuddy.backend.persistance.repositories.RoleRepository;
import com.devopsbuddy.backend.persistance.repositories.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class RepositoriesIntegrationTest {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    private static final int BASIC_PLAN_ID = 1;
    private static final long BASIC_ROLE_ID = 1;


    @BeforeEach
    public void init() {
        Assertions.assertNotNull(planRepository);
        Assertions.assertNotNull(roleRepository);
        Assertions.assertNotNull(userRepository);
    }

    @Test
    public void testCreateNewPlan() throws Exception {
        Plan basicPlan = createBasicPlan();
        planRepository.save(basicPlan);
        Plan retrievedPlan = planRepository.findById(BASIC_PLAN_ID).orElse(null);
        Assertions.assertNotNull(retrievedPlan);
    }

    @Test
    public void testCreateNewRole() throws Exception {
        Role basicRole = createBasicRole();
        roleRepository.save(basicRole);
        Role retrievedRole = roleRepository.findById(BASIC_ROLE_ID).orElse(null);
        Assertions.assertNotNull(retrievedRole);
    }

    @Test
    public void testCreateNewUser() throws Exception {
        Plan basicPlan = createBasicPlan();
        planRepository.save(basicPlan);

        User basicUser = createBasicUser();
        basicUser.setPlan(basicPlan);

        Role basicRole = createBasicRole();
        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole();
        userRole.setUser(basicUser);
        userRole.setRole(basicRole);
        userRoles.add(userRole);

        basicUser.getUserRoles().addAll(userRoles);

        for(UserRole ur : userRoles) {
            roleRepository.save(ur.getRole());
        }

        basicUser = userRepository.save(basicUser);
        User newlyCreatedUser = userRepository.findById(basicUser.getId()).orElse(null);
        Assertions.assertNotNull(newlyCreatedUser);
        Assertions.assertTrue(newlyCreatedUser.getId() != 0);
        Assertions.assertNotNull(newlyCreatedUser.getPlan());


    }

    //private methods
    private Plan createBasicPlan() {
        Plan plan = new Plan();
        plan.setId(BASIC_PLAN_ID);
        plan.setName("Basic");
        return plan;
    }

    private Role createBasicRole() {
        Role role = new Role();
        role.setId(BASIC_ROLE_ID);
        role.setName("ROLE_USER");
        return role;
    }

    private User createBasicUser() {
        User user = new User();
        user.setUsername("basicUser");
        user.setPassword("secret");
        user.setEmail("me@example.com");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPhoneNumber("123456789123");
        user.setCountry("GB");
        user.setEnabled(true);
        user.setDescription("A basic user");
        user.setProfileImageUrl("https://blabla.images.com/basicuser");

        return user;
    }

}
