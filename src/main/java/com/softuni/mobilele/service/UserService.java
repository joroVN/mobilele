package com.softuni.mobilele.service;

import com.softuni.mobilele.model.UserEntity;
import com.softuni.mobilele.model.dto.UserRegisterDTO;
import com.softuni.mobilele.model.mapper.UserMapper;
import com.softuni.mobilele.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserDetailsService userDetailService;
    private final EmailService emailService;
    private final SecurityContextRepository securityContextRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, UserDetailsService userDetailService, EmailService emailService, SecurityContextRepository securityContextRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userDetailService = userDetailService;
        this.emailService = emailService;
        this.securityContextRepository = securityContextRepository;
    }

    public void registerAndLogin(UserRegisterDTO userRegisterDTO,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        UserEntity newUser = userMapper.userDtoToUserEntity(userRegisterDTO);
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        userRepository.save(newUser);
        login(newUser, request, response);

//         TODO: email should sent to mailhog. Currently not working
        emailService.sendRegistrationEmail(newUser.getEmail(), newUser.getFirstName() + " " + newUser.getLastName());
    }


    private void login(UserEntity userEntity,
                       HttpServletRequest request,
                       HttpServletResponse response) {

        UserDetails userDetails = userDetailService.loadUserByUsername(userEntity.getEmail());

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
                .getContextHolderStrategy();

        SecurityContext context = securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(auth);
        securityContextHolderStrategy.setContext(context);

        securityContextRepository.saveContext(context, request, response);




    }


}
