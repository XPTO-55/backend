package br.com.cpa.spring.config.security.jwt;

import br.com.cpa.spring.models.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class UserPrincipal implements UserDetails {
    private List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
    private User user;
    private String userType;

    public UserPrincipal(User user) {
        this.user = user;
        this.userType = user.getUserType();
        this.authorities = user.getRoles().stream().map(role -> {
            return new SimpleGrantedAuthority("ROLE_".concat(role.getName()));
        }).collect(Collectors.toList());
    }

    public static UserPrincipal create(User user) {
        return new UserPrincipal(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
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
}
