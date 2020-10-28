package datn.security;

import datn.custom.domain.Account;
import datn.custom.domain.Admin;
import datn.enums.Role;
import datn.service.AccountService;
import datn.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AdminService adminService;
    

    @Override
    public UserDetails loadUserByUsername(String username) {

        Account account = accountService.findUser(username);
        if (account == null) {
            throw new UsernameNotFoundException("Cannot find user " + username);
        }

        if (!account.isActive()) {
            throw new UsernameNotFoundException("User is disable");
        }
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        Role role = account.getRole();
        authorities.add(new SimpleGrantedAuthority(SecurityUtils.getAuthorityFromRole(role)));

        CustomUserDetails.UserDetailsBuilder builder = CustomUserDetails
                .initBuilder()
                .setUsername(username)
                .setPassword(account.getPassword())
                .setAuthorities(authorities)
                .setAccountId(account.getId())
                .setLastLoginTime(new Date());
        {
            Admin admin = adminService.findByAccountId(account.getId());
            builder.setAdminId(admin.getId());
            builder.setName(admin.getUsername());
        }

        return builder.build();

    }

}
