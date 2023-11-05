package com.softuni.mobilele.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MobileleUserDetails extends User {

    private String fullName;

    public MobileleUserDetails(String username, String password,
                          Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public String getFullName() {
        return fullName;
    }

    public MobileleUserDetails setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
}
