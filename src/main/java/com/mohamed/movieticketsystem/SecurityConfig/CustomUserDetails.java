package com.mohamed.movieticketsystem.SecurityConfig;

import com.mohamed.movieticketsystem.entities.RegistretedUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Getter;
import lombok.Setter;
import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {
    private RegistretedUser user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user
                .getRoleList()
                .stream()
                .map(role -> new SimpleGrantedAuthority("Role_"+role))
                .collect(Collectors.toList());
    }



    @Override
    public String getPassword() {
        return user.getPassword();
    }

    public RegistretedUser getUser() {
        return user;
    }

    public void setUser(RegistretedUser user) {
        this.user = user;
    }

    @Override
    public String getUsername() {
        return user.getUserName();
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
