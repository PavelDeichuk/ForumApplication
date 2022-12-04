package com.pavel.forumapplication.Security;

import com.pavel.forumapplication.Entity.UsersEntity;
import com.pavel.forumapplication.Repository.UsersRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    private final UsersRepository usersRepository;

    public UserDetailService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity usersEntity = usersRepository.findByUsername(username)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Not found for username!");
                });
        UserDetails userDetails = User
                .builder()
                .username(usersEntity.getUsername())
                .password(usersEntity.getPassword())
                .roles(usersEntity.getRole())
                .build();
        return userDetails;
    }
}
