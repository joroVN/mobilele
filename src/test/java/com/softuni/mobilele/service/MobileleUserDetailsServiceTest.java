package com.softuni.mobilele.service;

import com.softuni.mobilele.model.UserEntity;
import com.softuni.mobilele.model.UserRoleEntity;
import com.softuni.mobilele.model.enums.UserRoleEnum;
import com.softuni.mobilele.model.user.MobileleUserDetails;
import com.softuni.mobilele.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MobileleUserDetailsServiceTest {
    @Mock
    private UserRepository mockUserRepo;
    private MobileleUserDetailService toTest;

    @BeforeEach
    void setUp() {
        toTest = new MobileleUserDetailService(
                mockUserRepo
        );


    }

    @Test
    void testLoadUserByUsername_UserExists() {
        //arrange
        UserEntity testUserEntity = new UserEntity()
                .setEmail("test@example.com")
                .setPassword("topsecret")
                .setFirstName("Pesho")
                .setLastName("Petrov")
                .setActive(true)
                .setImageUrl("https://image.com/image")
                .setUserRoles(
                        List.of(
                                new UserRoleEntity().setRole(UserRoleEnum.ADMIN),
                                new UserRoleEntity().setRole(UserRoleEnum.USER)
                        )
                );
        when(mockUserRepo.findByEmail(testUserEntity.getEmail()))
                .thenReturn(Optional.of(testUserEntity));

        //action
        MobileleUserDetails userDetails = (MobileleUserDetails) toTest.loadUserByUsername(testUserEntity.getEmail());

        //assert
        Assertions.assertEquals(testUserEntity.getEmail(), userDetails.getUsername());
        Assertions.assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(testUserEntity.getFirstName() + " " + testUserEntity.getLastName(),
                userDetails.getFullName());

        var authorities = userDetails.getAuthorities();
        Assertions.assertEquals(2, authorities.size());

        var authoritiesIter = authorities.iterator();
        Assertions.assertEquals("ROLE_" + UserRoleEnum.ADMIN.name(),
                authoritiesIter.next().getAuthority());
        Assertions.assertEquals("ROLE_" + UserRoleEnum.USER.name(),
                authoritiesIter.next().getAuthority());

    }

    @Test
    void testLoadUserByUsername_UserDoesNotExists() {
        //no need to arrange

        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername("not-existant@example.com")
        );
    }
}
