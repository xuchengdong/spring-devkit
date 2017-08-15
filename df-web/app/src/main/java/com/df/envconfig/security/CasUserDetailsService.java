package com.df.envconfig.security;

import com.df.domain.Role;
import com.df.domain.UserRole;
import com.df.service.UserRoleService;
import com.df.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuchengdong@qbao.com on 2017/8/7.
 */
@Component
public class CasUserDetailsService implements UserDetailsService {

    @Resource
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        Long userId = userService.findUserIdByUserName(username);
        UserRole userRole = userRoleService.findUserRoleByUserId(userId);

        if (userRole != null) {
            for (Role role : userRole.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
        }

        return new User(username, "123456", true,
                true, true,
                true, authorities);
    }
}
