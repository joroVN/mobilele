package com.softuni.mobilele.service;

import com.softuni.mobilele.model.UserEntity;
import com.softuni.mobilele.model.UserRoleEntity;
import com.softuni.mobilele.model.user.MobileleUserDetails;
import com.softuni.mobilele.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MobileleUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public MobileleUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails
    loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found!"));
    }

    private UserDetails map(UserEntity userEntity) {
        return new MobileleUserDetails(
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getUserRoles()
                        .stream()
                        .map(this::map)
                        .toList()
        )
                .setFullName(userEntity.getFirstName() + " " + userEntity.getLastName());
    }

    private GrantedAuthority map(UserRoleEntity userRoleEntity) {
        return new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getRole().name());
    }
}
