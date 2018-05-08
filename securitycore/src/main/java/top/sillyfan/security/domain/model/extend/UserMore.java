package top.sillyfan.security.domain.model.extend;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.sillyfan.security.constants.UserDef;

import java.util.Collection;
import java.util.Collections;

public abstract class UserMore implements UserDetails {

    @Override
    public boolean isEnabled() {
        return UserDef.UserStatus.Enabled.match(getStatus());
    }

    public abstract Integer getStatus();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
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
}
