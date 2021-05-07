package com.rapisolver.api.security.service;

import com.rapisolver.api.entities.Role;
import com.rapisolver.api.entities.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Getter
public class UserDetailsImpl implements UserDetails {

    private final Long id;

    private final String firstName;

    private final String email;

    //@JsonIgnore
    private final String password;

    private final Role role;

    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id,String firstName,String email,String password,Role role,List<GrantedAuthority> authorities) {
        this.id=id;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.role=role;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UserDetailsImpl build(User user) {

        return new UserDetailsImpl(user.getId(),user.getFirstName(),user.getEmail(),user.getPassword(), user.getRole(),Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName())));

    }
/*
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

 */
}
